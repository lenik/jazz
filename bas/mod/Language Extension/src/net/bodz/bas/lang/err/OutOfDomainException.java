package net.bodz.bas.lang.err;

public class OutOfDomainException extends IllegalArgumentException {

    private static final long serialVersionUID = -673378255529233385L;

    public OutOfDomainException() {
        super();
    }

    public OutOfDomainException(String s) {
        super(s);
    }

    // 3 (boundary n< at 0)
    public OutOfDomainException(String arg, Object actualval, Object boundval) {
        super(getMesg(arg, actualval, boundval));
    }

    // 3 (x)
    public OutOfDomainException(String arg, Object actualval) {
        super(getMesg(arg, actualval));
    }

    public static String getMesg(String arg, Object actualval, Object boundval) {
        return String.valueOf(actualval) + //
                " (boundary " + arg + " at " + boundval + ")";
    }

    public static String getMesg(String arg, Object actualval) {
        return String.valueOf(actualval) + //
                " (" + arg + ")";
    }

}
