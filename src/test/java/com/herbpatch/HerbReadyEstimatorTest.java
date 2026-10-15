package com.herbpatch;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HerbReadyEstimatorTest
{
    @Test
    public void allHerbVarietiesGrowAtExpectedTicks()
    {
        int[] growing = {4, 11, 18, 25, 32, 39, 46, 53, 60, 68, 75, 82, 89, 96, 103};
        for (int start : growing)
        {
            for (int stage = 0; stage < 4; stage++)
            {
                long doneAt = 1200L + (4L - stage) * 1200L;
                String saved = (start + stage) + ":1200";
                assertEquals(saved, HerbReadyEstimator.Result.GROWING,
                    HerbReadyEstimator.predict(saved, doneAt - 1, 0, 0, false));
                assertEquals(saved, HerbReadyEstimator.Result.READY,
                    HerbReadyEstimator.predict(saved, doneAt, 0, 0, false));
            }
        }
    }

    @Test
    public void directlyObservedHarvestableHerbsCount()
    {
        int[] mature = {8, 15, 22, 29, 36, 43, 50, 57, 64, 72, 79, 86, 93, 100, 107};
        for (int start : mature)
        {
            for (int state = start; state < start + 3; state++)
            {
                String saved = state + ":1200";
                assertEquals(saved, HerbReadyEstimator.Result.READY,
                    HerbReadyEstimator.predict(saved, 1200L, 0, 0, false));
            }
        }
    }

    @Test
    public void emptyDeadDiseasedAndWeedsNeverBecomeReady()
    {
        int[] nonHarvestable = {0, 1, 2, 3, 67, 128, 169, 170, 171, 172,
            173, 175, 176, 191, 192, 196, 197, 198, 200, 201, 203, 204, 255};
        for (int value : nonHarvestable)
        {
            String saved = value + ":1200";
            assertEquals(saved, HerbReadyEstimator.Result.OTHER,
                HerbReadyEstimator.predict(saved, 999999L, 0, 0, false));
        }
    }

    @Test
    public void invalidAndUnknownRecordsNeverBecomeReady()
    {
        String[] invalid = {null, "", "3", "4:x", "a:1200", "4:0", "4:-1",
            "4:1200:extra", "-3:1200", "256:1200", "220:1200", "4:10031"};
        for (String record : invalid)
        {
            assertEquals(String.valueOf(record), HerbReadyEstimator.Result.UNKNOWN,
                HerbReadyEstimator.predict(record, 10000L, 0, 0, false));
        }
    }

    @Test
    public void farmingTickOffsetMatchesTimeTrackingBoundaries()
    {
        String saved = "7:1250"; // final growing stage
        assertEquals(HerbReadyEstimator.Result.GROWING,
            HerbReadyEstimator.predict(saved, 2399L, 0, 0, false));
        assertEquals(HerbReadyEstimator.Result.READY,
            HerbReadyEstimator.predict(saved, 2400L, 0, 0, false));
        assertEquals(HerbReadyEstimator.Result.READY,
            HerbReadyEstimator.predict(saved, 2100L, 5, 20, false));
        assertEquals(HerbReadyEstimator.Result.GROWING,
            HerbReadyEstimator.predict(saved, 2099L, 5, 20, false));
        assertEquals(HerbReadyEstimator.Result.GROWING,
            HerbReadyEstimator.predict(saved, 2100L, 5, 0, false));
    }

    @Test
    public void leaguesWorldHasFourMinuteGrowthTicks()
    {
        String saved = "7:1000";
        assertEquals(HerbReadyEstimator.Result.GROWING,
            HerbReadyEstimator.predict(saved, 1199L, 0, 0, true));
        assertEquals(HerbReadyEstimator.Result.READY,
            HerbReadyEstimator.predict(saved, 1200L, 0, 0, true));
    }
}
