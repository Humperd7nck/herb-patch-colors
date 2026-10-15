package com.herbpatch;

/** Determines whether the optional herb status indicator should be visible. */
final class HerbsReadyHudState
{
    enum State
    {
        HIDDEN,
        READY,
        NOT_READY
    }

    private HerbsReadyHudState()
    {
    }

    static State resolve(boolean hudEnabled, boolean allReady, boolean showNotReady)
    {
        if (!hudEnabled)
        {
            return State.HIDDEN;
        }
        if (allReady)
        {
            return State.READY;
        }
        return showNotReady ? State.NOT_READY : State.HIDDEN;
    }
}
