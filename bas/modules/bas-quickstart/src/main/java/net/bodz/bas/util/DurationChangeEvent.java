package net.bodz.bas.util;

import java.util.EventObject;

public class DurationChangeEvent extends EventObject {

    private static final long serialVersionUID = 6974126689767434701L;

    public final long oldDuration;
    public final long newDuration;

    public DurationChangeEvent(Object source, long oldDuration, long newDuration) {
        super(source);
        this.oldDuration = oldDuration;
        this.newDuration = newDuration;
    }

    public long getIncrement() {
        return newDuration - oldDuration;
    }

}
