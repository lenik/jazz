package net.bodz.bas.exceptions;

import net.bodz.bas.module.ModuleInfo;
import net.bodz.bas.nls_2.NLS;

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
            return String.valueOf(argType) + NLS.getString("_expect") + expected; //$NON-NLS-1$
    }

    private static NLS NLS = ModuleInfo.getInstance().getNLS();

}
