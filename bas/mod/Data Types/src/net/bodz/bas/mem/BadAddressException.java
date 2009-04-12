package net.bodz.bas.mem;

import net.bodz.bas.nls.TypesNLS;

public class BadAddressException extends AccessException {

    private static final long serialVersionUID = 8898684852756951338L;

    public BadAddressException() {
        super();
    }

    public BadAddressException(long addr, long boundary) {
        super(String.format(TypesNLS.getString("BadAddressException.addrExceeds_xx"), addr, //$NON-NLS-1$
                boundary));
    }

}
