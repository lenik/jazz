package net.bodz.bas.lang;

public class ControlExit
        extends ControlBreak {

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
