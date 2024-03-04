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

    default void enterln() {
        println();
        enter();
    }

    default void enterln(String s) {
        println(s);
        enter();
    }

    default void leaveln() {
        leave();
        println();
    }

    default void leaveln(String s) {
        leave();
        println(s);
    }

    default void lnleave() {
        println();
        leave();
    }

    default void lnleave(String s) {
        println(s);
        leave();
    }

    ITreeOut NULL = new NullTreeOut();

}
