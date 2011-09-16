package net.bodz.bas.sio;

import net.bodz.bas.sio.util.ITextIndention;

public interface IStackedOut
        extends IPrintOut {

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
