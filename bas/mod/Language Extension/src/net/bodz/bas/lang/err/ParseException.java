package net.bodz.bas.lang.err;

public class ParseException extends Exception {

    private static final long serialVersionUID = 7260313523533602858L;

    public ParseException() {
        super();
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(Class<?> unparsableType, String unparsableText) {
        this("don't know how to parse " + unparsableType + ": "
                + unparsableText);
    }

}
