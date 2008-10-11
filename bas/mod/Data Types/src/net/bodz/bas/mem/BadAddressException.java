package net.bodz.bas.mem;

public class BadAddressException extends AccessException {

    private static final long serialVersionUID = 8898684852756951338L;

    public BadAddressException() {
        super();
    }

    public BadAddressException(long addr, long boundary) {
        super("address " + addr + " exceeds the boundary " + boundary);
    }

}
