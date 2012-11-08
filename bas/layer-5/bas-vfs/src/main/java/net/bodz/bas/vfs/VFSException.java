package net.bodz.bas.vfs;

public class VFSException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VFSException() {
        super();
    }

    public VFSException(String message, Throwable cause) {
        super(message, cause);
    }

    public VFSException(String message) {
        super(message);
    }

    public VFSException(Throwable cause) {
        super(cause);
    }

}
