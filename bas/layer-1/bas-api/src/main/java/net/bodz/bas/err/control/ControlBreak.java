package net.bodz.bas.err.control;

public class ControlBreak
        extends Control {

    private static final long serialVersionUID = 1L;

    private final Object boundary;

    public ControlBreak() {
        this.boundary = null;
    }

    public ControlBreak(Object boundary) {
        this.boundary = boundary;
    }

    /**
     * It's common to see a boundary is a scope label.
     */
    public Object getBoundary() {
        return boundary;
    }

}
