package net.bodz.bas.exceptions;

import net.bodz.bas.i18n.nls_2.NLS;
import net.bodz.bas.lang.ModuleInfo;

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

    private static final long serialVersionUID = -673378255529233385L;

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
        return NLS.format("_s_boundar", actualValue, arg, boundaryValue);
    }

    // 3 (n<)
    public static String format(String arg, Object actualValue) {
        return String.valueOf(actualValue) + //
                " (" + arg + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static NLS NLS = ModuleInfo.getInstance().getNLS();

}
