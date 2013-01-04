package net.bodz.bas.program.skel;

import net.bodz.bas.err.ParseException;

public class CLISyntaxException
        extends ParseException {

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
