package net.bodz.bas.lang.err;

import net.bodz.bas.nls.LangNLS;

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
        this(LangNLS.getString("ParseException.unknownParse") + unparsableType + ": " //$NON-NLS-1$ //$NON-NLS-2$
                + unparsableText);
    }

}
