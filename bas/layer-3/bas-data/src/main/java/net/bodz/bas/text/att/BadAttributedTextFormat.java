package net.bodz.bas.text.att;

import net.bodz.bas.err.ParseException;

public class BadAttributedTextFormat
        extends ParseException {

    private static final long serialVersionUID = 1L;

    public BadAttributedTextFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public BadAttributedTextFormat(String message) {
        super(message);
    }

    public BadAttributedTextFormat(Throwable cause) {
        super(cause);
    }

}
