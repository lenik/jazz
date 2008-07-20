package net.bodz.bas.lang.err;

public class OutOfDomainException extends IllegalArgumentException {

    private static final long serialVersionUID = -673378255529233385L;

    public OutOfDomainException() {
        super();
    }

    public OutOfDomainException(String s) {
        super(s);
    }

    public OutOfDomainException(String arg, Object actualval, Object boundval) {
        super(arg + " out of domain: " + actualval + " (boundary point: "
                + boundval + ")");
    }

    public OutOfDomainException(String arg, Object actualval) {
        super(arg + " out of domain: " + actualval);
    }

}
