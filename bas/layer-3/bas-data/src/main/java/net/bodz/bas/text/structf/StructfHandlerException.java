package net.bodz.bas.text.structf;

import net.bodz.bas.err.ProcessException;

public class StructfHandlerException
        extends ProcessException {

    private static final long serialVersionUID = 1L;

    public StructfHandlerException() {
        super();
    }

    public StructfHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public StructfHandlerException(String message) {
        super(message);
    }

    public StructfHandlerException(Throwable cause) {
        super(cause);
    }

}
