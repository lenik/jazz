package net.bodz.bas.util;

import java.util.EventObject;

public class ProgressChangeEvent extends EventObject {

    private static final long serialVersionUID = 2440562208883770724L;

    private double            progress;

    public ProgressChangeEvent(Object source, double progress) {
        super(source);
        this.progress = progress;
    }

    public double getProgress() {
        return progress;
    }

}
