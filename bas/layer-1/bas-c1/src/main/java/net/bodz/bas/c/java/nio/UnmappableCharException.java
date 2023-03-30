package net.bodz.bas.c.java.nio;

import java.nio.charset.CoderResult;

import net.bodz.bas.err.ParseException;

public class UnmappableCharException
        extends ParseException {

    private static final long serialVersionUID = 1L;

    public UnmappableCharException() {
        super();
    }

    public UnmappableCharException(String name, String message) {
        super(name, message);
    }

    public UnmappableCharException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmappableCharException(String message) {
        super(message);
    }

    public UnmappableCharException(Throwable cause) {
        super(cause);
    }

    public UnmappableCharException(CoderResult result) {
        super(result.toString());
    }

}
