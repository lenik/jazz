package net.bodz.swt.c3.pageflow;

import java.util.EventObject;

public class HistoryChangeEvent
        extends EventObject {

    private static final long serialVersionUID = -160224623702632279L;

    public static final int JUMP = 1;
    public static final int DROP_BACKWARDS = 2;
    public static final int DROP_FORWARDS = 4;

    private final int type;

    public HistoryChangeEvent(History history, int type) {
        super(history);
        this.type = type;
    }

    public History getHistory() {
        return (History) getSource();
    }

    public int getType() {
        return type;
    }

}
