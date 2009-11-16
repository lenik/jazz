package net.bodz.bas.lang.err;

import net.bodz.bas.nls.LangNLS;

public class IllegalArgumentTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = -4429030040543851015L;

    public IllegalArgumentTypeException() {
        super();
    }

    public IllegalArgumentTypeException(String s) {
        super(s);
    }

    public IllegalArgumentTypeException(Class<?> argType) {
        super(mesgOf(argType, null));
    }

    public IllegalArgumentTypeException(Class<?> argType, Object expectedTypeString) {
        super(mesgOf(argType, expectedTypeString));
    }

    public IllegalArgumentTypeException(Object arg) {
        super(mesgOf(arg, null));
    }

    public IllegalArgumentTypeException(Object arg, Object expectedTypeString) {
        super(mesgOf(arg, expectedTypeString));
    }

    static String mesgOf(Object arg, Object expected) {
        Class<?> argType;
        if (arg == null)
            argType = null;
        else
            argType = arg.getClass();
        return mesgOf(argType, expected);
    }

    static String mesgOf(Class<?> argType, Object expected) {
        if (expected == null)
            return String.valueOf(argType);
        else
            return String.valueOf(argType) + LangNLS.getString("IllegalArgumentTypeException.expect") + expected; //$NON-NLS-1$
    }

}
