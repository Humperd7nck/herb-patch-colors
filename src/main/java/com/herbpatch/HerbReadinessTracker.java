package com.herbpatch;

import com.herbpatch.constants.HerbPatch;
import java.time.Instant;
import java.util.EnumSet;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.WorldType;
import net.runelite.client.config.ConfigManager;

/**
 * Read-only, per-RuneScape-profile summary from RuneLite Time Tracking data.
 * Values are snapshots that may be stale when patches have not been revisited.
 */
public class HerbReadinessTracker
{
    static final int TOTAL_PATCHES = HerbPatch.values().length;
    private static final String TRACKING_GROUP = "timetracking";
    private static final long REFRESH_SECONDS = 5L;

    private final Client client;
    private final ConfigManager configManager;

    private String cachedProfileKey;
    private long lastReadSeconds = Long.MIN_VALUE;
    private Readiness cached = new Readiness(0, 0);

    @Inject
    public HerbReadinessTracker(Client client, ConfigManager configManager)
    {
        this.client = client;
        this.configManager = configManager;
    }

    Readiness getReadiness()
    {
        long now = Instant.now().getEpochSecond();
        String profile = configManager.getRSProfileKey();
        if (profile == null || profile.isEmpty())
        {
            return new Readiness(0, 0);
        }

        if (profile.equals(cachedProfileKey)
            && now >= lastReadSeconds && now - lastReadSeconds < REFRESH_SECONDS)
        {
            return cached;
        }

        int offset = parseNumberOrZero(configManager.getRSProfileConfiguration(
            TRACKING_GROUP, "farmTickOffset"));
        int precision = parseNumberOrZero(configManager.getRSProfileConfiguration(
            TRACKING_GROUP, "farmTickOffsetPrecision"));
        EnumSet<WorldType> worldTypes = client.getWorldType();
        boolean leagues = worldTypes != null
            && worldTypes.contains(WorldType.SEASONAL)
            && !worldTypes.contains(WorldType.DEADMAN);

        int ready = 0;
        int known = 0;
        for (HerbPatch patch : HerbPatch.values())
        {
            String key = patch.trackingRegionId + "." + patch.varbit;
            String record = configManager.getRSProfileConfiguration(TRACKING_GROUP, key);
            HerbReadyEstimator.Result result =
                HerbReadyEstimator.predict(record, now, offset, precision, leagues);
            if (result != HerbReadyEstimator.Result.UNKNOWN)
            {
                known++;
            }
            if (result == HerbReadyEstimator.Result.READY)
            {
                ready++;
            }
        }

        cachedProfileKey = profile;
        lastReadSeconds = now;
        cached = new Readiness(ready, known);
        return cached;
    }

    private static int parseNumberOrZero(String input)
    {
        if (input == null)
        {
            return 0;
        }
        try
        {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException ignored)
        {
            return 0;
        }
    }

    static final class Readiness
    {
        final int ready;
        final int known;

        Readiness(int ready, int known)
        {
            this.ready = ready;
            this.known = known;
        }

        boolean allReady()
        {
            return ready == TOTAL_PATCHES;
        }

        int remaining()
        {
            return TOTAL_PATCHES - ready;
        }
    }
}
