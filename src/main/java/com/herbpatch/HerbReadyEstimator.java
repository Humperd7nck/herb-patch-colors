package com.herbpatch;

/**
 * Predicts maturity from RuneLite Time Tracking's saved herb patch observations.
 * Data describes the last observed patch state, not an authoritative remote state.
 */
final class HerbReadyEstimator
{
    // RuneLite FarmingTracker/PatchImplementation.HERB herb varieties.
    private static final int[] GROWING_STARTS = {
        4, 11, 18, 25, 32, 39, 46, 53, 60, 68, 75, 82, 89, 96, 103
    };

    private static final int[] MATURE_STARTS = {
        8, 15, 22, 29, 36, 43, 50, 57, 64, 72, 79, 86, 93, 100, 107
    };

    enum Result
    {
        READY,
        GROWING,
        OTHER,
        UNKNOWN
    }

    private HerbReadyEstimator()
    {
    }

    static Result predict(String record, long nowSeconds,
                          int offsetMinutes, int offsetPrecisionMinutes,
                          boolean leaguesWorld)
    {
        if (record == null)
        {
            return Result.UNKNOWN;
        }

        String[] parts = record.split(":", -1);
        if (parts.length != 2)
        {
            return Result.UNKNOWN;
        }

        final int value;
        final long observedAt;
        try
        {
            value = Integer.parseInt(parts[0]);
            observedAt = Long.parseLong(parts[1]);
        }
        catch (NumberFormatException e)
        {
            return Result.UNKNOWN;
        }

        if (observedAt <= 0 || observedAt > nowSeconds + 30L)
        {
            return Result.UNKNOWN;
        }

        for (int start : MATURE_STARTS)
        {
            if (value >= start && value < start + 3)
            {
                return Result.READY;
            }
        }

        for (int start : GROWING_STARTS)
        {
            if (value >= start && value < start + 4)
            {
                int stage = value - start;
                int tickMinutes = leaguesWorld ? 4 : 20;
                long tickSeconds = tickMinutes * 60L;
                long offsetSeconds = (offsetPrecisionMinutes >= tickMinutes || offsetPrecisionMinutes >= 40)
                    ? Math.floorMod(offsetMinutes, tickMinutes) * 60L : 0L;
                long observedTick = Math.floorDiv(observedAt + offsetSeconds, tickSeconds)
                    * tickSeconds - offsetSeconds;
                long readyAt = observedTick + (4L - stage) * tickSeconds;
                return nowSeconds >= readyAt ? Result.READY : Result.GROWING;
            }
        }

        // Known but not harvestable: empty, weeds, diseased, dead, or goutweed.
        if (value >= 0 && value <= 255 && value != 220)
        {
            return Result.OTHER;
        }
        return Result.UNKNOWN;
    }
}
