package net.bodz.bas.io;

import net.bodz.bas.io.impl.NullTreeOut;

public interface ITreeOut
        extends
            IPrintOut,
            IIndented {

    /**
     * @return non-<code>null</code> {@link ITextIndention}.
     */
    ITextIndention getTextIndention();

    default void enterln(String s) {
        println(s);
        enter();
    }

    default void leaveln(String s) {
        leave();
        println(s);
    }

    default void lnleave(String s) {
        println(s);
        leave();
    }

    ITreeOut NULL = new NullTreeOut();

}
