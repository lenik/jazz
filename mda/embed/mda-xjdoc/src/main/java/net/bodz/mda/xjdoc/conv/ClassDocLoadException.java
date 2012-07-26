package net.bodz.mda.xjdoc.conv;

import net.bodz.bas.err.IllegalEnvironConfigException;

public class ClassDocLoadException
        extends IllegalEnvironConfigException {

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
