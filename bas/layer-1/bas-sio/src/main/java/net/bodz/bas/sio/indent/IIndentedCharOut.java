package net.bodz.bas.sio.indent;

import net.bodz.bas.sio.IPrintCharOut;

public interface IIndentedCharOut
        extends IPrintCharOut {

    /**
     * @return non-<code>null</code> {@link ITextIndention}.
     */
    ITextIndention getTextIndention();

    /**
     * @return The indent-level after enterred.
     */
    int enter();

    /**
     * @return The indent-level after left.
     */
    int leave();

}
