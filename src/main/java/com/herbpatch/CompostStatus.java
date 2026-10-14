package com.herbpatch;

import com.herbpatch.constants.HerbPatch;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;

/** Reads RuneLite Time Tracking's per-profile compost record without modifying it. */
public class CompostStatus {
    private final ConfigManager configuration;

    @Inject
    public CompostStatus(ConfigManager configuration) {
        this.configuration = configuration;
    }

    public boolean isComposted(HerbPatch patch) {
        // Mirrors RuneLite FarmingPatch.configKey and CompostTracker.configKey.
        String key = patch.trackingRegionId + "." + patch.varbit + ".compost";
        String status = configuration.getRSProfileConfiguration("timetracking", key);
        return "COMPOST".equals(status) || "SUPERCOMPOST".equals(status)
            || "ULTRACOMPOST".equals(status);
    }
}
