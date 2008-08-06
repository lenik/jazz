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
        super(String.valueOf(actualval) + " (boundary " + arg + " at "
                + boundval + ")");
    }

    // 3 (x)
    public OutOfDomainException(String arg, Object actualval) {
        super(String.valueOf(actualval) + " (" + arg + ")");
    }

}
