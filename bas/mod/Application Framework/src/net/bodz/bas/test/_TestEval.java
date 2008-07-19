package net.bodz.bas.test;

public abstract class _TestEval<T> implements TestEval<T> {

    private boolean breakpoint;

    public boolean isBreakpoint() {
        return breakpoint;
    }

    public void setBreakpoint(boolean breakpoint) {
        this.breakpoint = breakpoint;
    }

}
