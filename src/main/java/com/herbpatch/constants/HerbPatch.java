package com.herbpatch.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static net.runelite.api.gameval.ObjectID.*;
// Transmog controllers for various farming patches
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_A; // Troll Stronghold, Weiss
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_B; // Harmony Island
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_D; // Falador, Port Phasmatys, Hosidius, Ardougne, Catherby, Varlamore
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_E; // Farming Guild

/**
 * Enum containing ObjectIDs and VarbitIDs for the various herb patches around Gilenor
 */
public enum HerbPatch {
    FALADOR    (FARMING_HERB_PATCH_1,  FARMING_TRANSMIT_D, 12083),
    CATHERBY   (FARMING_HERB_PATCH_2,  FARMING_TRANSMIT_D, 11062),
    ARDOUGNE   (FARMING_HERB_PATCH_3,  FARMING_TRANSMIT_D, 10548),
    PHASMATYS  (FARMING_HERB_PATCH_4,  FARMING_TRANSMIT_D, 14391),
    HARMONY    (FARMING_HERB_PATCH_5,  FARMING_TRANSMIT_B, 15148),
    STRONGHOLD (MYARM_HERBPATCH,       FARMING_TRANSMIT_A, 11321),
    HOSIDIUS   (FARMING_HERB_PATCH_6,  FARMING_TRANSMIT_D, 6967),
    WEISS      (33176, FARMING_TRANSMIT_A, 11325),
    GUILD      (FARMING_HERB_PATCH_7,  FARMING_TRANSMIT_E, 4922),
    VARLAMORE  (FARMING_HERB_PATCH_8,  FARMING_TRANSMIT_D, 6192);

    public final int objectId;
    public final int varbit;
    public final int trackingRegionId;

    HerbPatch(int objectId, int varbit, int trackingRegionId) {
        this.objectId = objectId;
        this.varbit = varbit;
        this.trackingRegionId = trackingRegionId;
    }

    private static final Map<Integer, HerbPatch> BY_ID = Arrays.stream(values()).collect(Collectors.toMap(p -> p.objectId, p -> p));

    public static HerbPatch byObjectId(int id) {
        return BY_ID.get(id);
    }
}
