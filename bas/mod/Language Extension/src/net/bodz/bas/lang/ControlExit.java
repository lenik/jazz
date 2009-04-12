package net.bodz.bas.lang;

public class ControlExit extends Control {

    private static final long serialVersionUID = 7374615167936405650L;

    public ControlExit() {
        super();
    }

    public ControlExit(int status) {
        super(status);
    }

    public int getStatus() {
        return (Integer) getDetail();
    }

}
