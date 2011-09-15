package net.bodz.bas.lang.control;

public class ControlExit
        extends Control {

    private static final long serialVersionUID = 1L;

    private int status;

    public ControlExit() {
    }

    public ControlExit(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
