package net.bodz.bas.traits.provider;

import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public class TraitsProviderException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public TraitsProviderException() {
        super();
    }

    public TraitsProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public TraitsProviderException(String message) {
        super(message);
    }

    public TraitsProviderException(Throwable cause) {
        super(cause);
    }

}
