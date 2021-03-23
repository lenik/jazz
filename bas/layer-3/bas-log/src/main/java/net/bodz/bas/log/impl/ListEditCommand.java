package net.bodz.bas.log.impl;

import java.io.Serializable;

public class ListEditCommand
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListEditType type;
    public int start;
    public int len;

    public ListEditCommand() {
        this.type = ListEditType.CLEAR;
    }

    public ListEditCommand(ListEditType type, int start) {
        this(type, start, 1);
    }

    public ListEditCommand(ListEditType type, int start, int len) {
        this.type = type;
        this.start = start;
        this.len = len;
    }

}
