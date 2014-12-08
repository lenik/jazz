package net.bodz.mda.xjdoc;

import net.bodz.bas.err.IllegalConfigException;

public class XjdocLoaderException
        extends IllegalConfigException {

    private static final long serialVersionUID = 1L;

    public XjdocLoaderException() {
        super();
    }

    public XjdocLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public XjdocLoaderException(String message) {
        super(message);
    }

    public XjdocLoaderException(Throwable cause) {
        super(cause);
    }

}
