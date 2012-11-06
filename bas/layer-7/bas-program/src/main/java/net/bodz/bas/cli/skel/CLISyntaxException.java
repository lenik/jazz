package net.bodz.bas.cli.skel;

public class CLISyntaxException
        extends Exception {

    private static final long serialVersionUID = -7537357433011384587L;

    public CLISyntaxException() {
        super();
    }

    public CLISyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLISyntaxException(String message) {
        super(message);
    }

    public CLISyntaxException(Throwable cause) {
        super(cause);
    }

}
