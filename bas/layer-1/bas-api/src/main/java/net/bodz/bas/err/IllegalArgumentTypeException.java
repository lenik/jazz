package net.bodz.bas.err;

public class IllegalArgumentTypeException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public IllegalArgumentTypeException() {
        super();
    }

    public IllegalArgumentTypeException(String s) {
        super(s);
    }

    public IllegalArgumentTypeException(Class<?> argType) {
        super(format(argType, null));
    }

    public IllegalArgumentTypeException(Class<?> argType, Object expectedTypeString) {
        super(format(argType, expectedTypeString));
    }

    public IllegalArgumentTypeException(Object arg) {
        super(format(arg, null));
    }

    public IllegalArgumentTypeException(Object arg, Object expectedTypeString) {
        super(format(arg, expectedTypeString));
    }

    static String format(Object arg, Object expected) {
        Class<?> argType;
        if (arg == null)
            argType = null;
        else
            argType = arg.getClass();
        return format(argType, expected);
    }

    static String format(Class<?> argType, Object expected) {
        if (expected == null)
            return String.valueOf(argType);
        else
            return String.format("%s: expect %s", argType, expected);

    }

}
