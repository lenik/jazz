package net.bodz.bas.err;

public class IllegalSubclassException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public IllegalSubclassException() {
        super();
    }

    public IllegalSubclassException(String s) {
        super(s);
    }

    public IllegalSubclassException(String parameterName, Object actual, Class<?> expectedType) {
        super(String.format("%s: %s, expected type: %s", //
                parameterName, actual == null ? null : actual.getClass(), expectedType));
    }

}
