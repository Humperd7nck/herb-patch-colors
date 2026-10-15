package com.herbpatch;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HerbsReadyHudStateTest
{
    @Test
    public void disabledHudNeverRenders()
    {
        assertEquals(HerbsReadyHudState.State.HIDDEN,
            HerbsReadyHudState.resolve(false, true, false));
        assertEquals(HerbsReadyHudState.State.HIDDEN,
            HerbsReadyHudState.resolve(false, false, true));
    }

    @Test
    public void readyStateShowsWhenHudEnabled()
    {
        assertEquals(HerbsReadyHudState.State.READY,
            HerbsReadyHudState.resolve(true, true, false));
        assertEquals(HerbsReadyHudState.State.READY,
            HerbsReadyHudState.resolve(true, true, true));
    }

    @Test
    public void notReadyStateIsOptional()
    {
        assertEquals(HerbsReadyHudState.State.HIDDEN,
            HerbsReadyHudState.resolve(true, false, false));
        assertEquals(HerbsReadyHudState.State.NOT_READY,
            HerbsReadyHudState.resolve(true, false, true));
    }
}
