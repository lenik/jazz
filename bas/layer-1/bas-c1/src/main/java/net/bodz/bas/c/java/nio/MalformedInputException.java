package net.bodz.bas.c.java.nio;

import java.nio.charset.CoderResult;

import net.bodz.bas.err.ParseException;

public class MalformedInputException
        extends ParseException {

    private static final long serialVersionUID = 1L;

    public MalformedInputException() {
        super();
    }

    public MalformedInputException(String name, String message) {
        super(name, message);
    }

    public MalformedInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedInputException(String message) {
        super(message);
    }

    public MalformedInputException(Throwable cause) {
        super(cause);
    }

    public MalformedInputException(CoderResult result) {
        super(result.toString());
    }

}
