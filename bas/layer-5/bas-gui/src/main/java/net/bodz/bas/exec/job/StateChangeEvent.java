package net.bodz.bas.exec.job;

import java.util.EventObject;

public class StateChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 3764143297831749161L;

    private int type;

    public StateChangeEvent(Object source) {
        super(source);
    }

    public StateChangeEvent(Object source, int type) {
        super(source);
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
