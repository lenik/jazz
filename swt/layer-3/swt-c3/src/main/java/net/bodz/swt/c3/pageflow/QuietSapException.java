package net.bodz.swt.c3.pageflow;

public class QuietSapException
        extends PageException {

    private static final long serialVersionUID = -4620189262989807707L;

    public QuietSapException() {
        super();
    }

    public QuietSapException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuietSapException(String message) {
        super(message);
    }

    public QuietSapException(Throwable cause) {
        super(cause);
    }

    @Override
    public boolean isQuiet() {
        return true;
    }

}
