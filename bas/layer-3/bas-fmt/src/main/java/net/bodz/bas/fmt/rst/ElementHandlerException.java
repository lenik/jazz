package net.bodz.bas.fmt.rst;

import net.bodz.bas.err.ProcessException;

public class ElementHandlerException
        extends ProcessException {

    private static final long serialVersionUID = 1L;

    public ElementHandlerException() {
        super();
    }

    public ElementHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementHandlerException(String message) {
        super(message);
    }

    public ElementHandlerException(Throwable cause) {
        super(cause);
    }

}
