package net.bodz.bas.repr.resource;

public class ResourceResolveException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceResolveException() {
        super();
    }

    public ResourceResolveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceResolveException(String message) {
        super(message);
    }

    public ResourceResolveException(Throwable cause) {
        super(cause);
    }

}
