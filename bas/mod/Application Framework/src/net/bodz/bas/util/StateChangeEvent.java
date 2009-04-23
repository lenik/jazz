package net.bodz.bas.util;

import java.util.EventObject;

public class StateChangeEvent extends EventObject {

    private static final long serialVersionUID = 3764143297831749161L;

    public StateChangeEvent(Object source) {
        super(source);
    }

}
