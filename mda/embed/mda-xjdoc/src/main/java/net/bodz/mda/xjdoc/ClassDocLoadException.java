package net.bodz.mda.xjdoc;

import net.bodz.bas.err.IllegalConfigException;

public class ClassDocLoadException
        extends IllegalConfigException {

    private static final long serialVersionUID = 1L;

    public ClassDocLoadException() {
        super();
    }

    public ClassDocLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassDocLoadException(String message) {
        super(message);
    }

    public ClassDocLoadException(Throwable cause) {
        super(cause);
    }

}
