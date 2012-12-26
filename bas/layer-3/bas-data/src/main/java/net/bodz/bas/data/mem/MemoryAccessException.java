package net.bodz.bas.data.mem;

public class MemoryAccessException
        extends Exception {

    private static final long serialVersionUID = -1L;

    public MemoryAccessException() {
        super();
    }

    public MemoryAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoryAccessException(String message) {
        super(message);
    }

    public MemoryAccessException(Throwable cause) {
        super(cause);
    }

}
