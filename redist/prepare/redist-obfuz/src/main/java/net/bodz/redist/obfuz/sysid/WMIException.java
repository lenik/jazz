package net.bodz.redist.obfuz.sysid;

public class WMIException
        extends Exception {

    private static final long serialVersionUID = -5230364232243534404L;

    public WMIException() {
        super();
    }

    public WMIException(String message, Throwable cause) {
        super(message, cause);
    }

    public WMIException(String message) {
        super(message);
    }

    public WMIException(Throwable cause) {
        super(cause);
    }

}
