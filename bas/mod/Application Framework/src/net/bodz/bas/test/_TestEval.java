package net.bodz.bas.test;

public abstract class _TestEval<T> implements TestEval<T> {

    private boolean breakpoint;

    /**
     * System property "failBP" must set to 1 to enable breakpoint hint feature.
     */
    public boolean isBreakpoint() {
        return breakpoint;
    }

    public void setBreakpoint(boolean breakpoint) {
        this.breakpoint = breakpoint;
    }

}
