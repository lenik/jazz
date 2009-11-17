package net.bodz.bas.api.exceptions;

import net.bodz.bas.api.ModuleInfo;
import net.bodz.bas.i18n.NLS;

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

    public OutOfDomainException( String s ) {
        super( s );
    }

    // 3 (boundary n< at 0)
    public OutOfDomainException( String arg, Object actualval, Object boundval ) {
        super( getMesg( arg, actualval, boundval ) );
    }

    // 3 (x)
    public OutOfDomainException( String arg, Object actualval ) {
        super( getMesg( arg, actualval ) );
    }

    public static String getMesg( String arg, Object actualval, Object boundval ) {
        return String.valueOf( actualval )
                + //
                nls.getString( "OutOfDomainException.boundary" ) + arg + nls.getString( "OutOfDomainException.at" ) + boundval + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public static String getMesg( String arg, Object actualval ) {
        return String.valueOf( actualval ) + //
                " (" + arg + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static NLS nls = ModuleInfo.getInstance().getNLS();

}
