package net.bodz.bas.data.mem;

public class BadAddressException
        extends MemoryAccessException {

    private static final long serialVersionUID = 8898684852756951338L;

    public BadAddressException() {
        super();
    }

    public BadAddressException(long addr, long boundary) {
        super(String.format("Address %d exceeds the boundary %d", addr, boundary));
    }

}
