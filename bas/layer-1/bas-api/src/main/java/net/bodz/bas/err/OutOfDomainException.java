package net.bodz.bas.err;

/**
 * oode Template:
 * 
 * oode:
 * 
 * <pre>
 *  if (${var:localVar} ${cursor}&lt; ${boundary:0})
 *     throw new OutOfDomainException(&quot;${var}&quot;, ${var}, ${boundary});
 * </pre>
 * 
 * npe:
 * 
 * <pre>
 * if (${var:localVar} == null)
 *             throw new NullPointerException(&quot;${var}&quot;);
 *</pre>
 */
public class OutOfDomainException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public OutOfDomainException() {
        super();
    }

    public OutOfDomainException(String s) {
        super(s);
    }

    // 3 (boundary n< at 0)
    public OutOfDomainException(String arg, Object actualValue, Object boundaryValue) {
        super(format(arg, actualValue, boundaryValue));
    }

    // 3 (n<)
    public OutOfDomainException(String arg, Object actualValue) {
        super(format(arg, actualValue));
    }

    // 3 (boundary n< at 0)
    public static String format(String arg, Object actualValue, Object boundaryValue) {
        return String.format("%s (boundary %s at %s)", actualValue, arg, boundaryValue);
    }

    // 3 (n<)
    public static String format(String arg, Object actualValue) {
        return String.valueOf(actualValue) + //
                " (" + arg + ")";
    }

}
