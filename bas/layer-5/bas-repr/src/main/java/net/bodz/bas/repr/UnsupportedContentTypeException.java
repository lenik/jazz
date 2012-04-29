package net.bodz.bas.repr;

public class UnsupportedContentTypeException
        extends RESTfulException {

    private static final long serialVersionUID = 1L;

    public UnsupportedContentTypeException() {
        super();
    }

    public UnsupportedContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedContentTypeException(String message) {
        super(message);
    }

    public UnsupportedContentTypeException(Throwable cause) {
        super(cause);
    }

}
