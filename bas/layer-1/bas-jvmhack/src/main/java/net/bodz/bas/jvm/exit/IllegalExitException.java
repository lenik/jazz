package net.bodz.bas.jvm.exit;

public class IllegalExitException
        extends Exception {

    private static final long serialVersionUID = 1L;

    private final int status;

    public IllegalExitException(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
