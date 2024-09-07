package net.bodz.lily.entity.ws;

public class RequestHandlerException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public RequestHandlerException() {
        super();
    }

    public RequestHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHandlerException(String message) {
        super(message);
    }

    public RequestHandlerException(Throwable cause) {
        super(cause);
    }

}
