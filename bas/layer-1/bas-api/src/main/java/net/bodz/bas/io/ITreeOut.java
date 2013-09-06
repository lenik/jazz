package net.bodz.bas.io;

import net.bodz.bas.io.impl.NullTreeOut;

public interface ITreeOut
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

    ITreeOut NULL = new NullTreeOut();

}
