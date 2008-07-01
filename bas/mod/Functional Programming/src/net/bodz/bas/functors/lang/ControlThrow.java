package net.bodz.bas.functors.lang;

import net.bodz.bas.lang.Control;

public class ControlThrow extends Control {

    private static final long serialVersionUID = 8305400243392913319L;

    public ControlThrow(Throwable t) {
        super(t);
    }

    public ControlThrow(Control cause, Throwable t) {
        super(cause, t);
    }

    public Throwable getThrowable() {
        return (Throwable) detail;
    }

    public void _throw() throws Throwable {
        throw getThrowable();
    }

}
