package net.bodz.bas.fn.legacy;

/**
 * @see Runnable
 */
@Deprecated
public abstract class Proc0
        implements Func0<Void> {

    @Override
    public final Void eval() {
        exec();
        return null;
    }

    public abstract void exec();

}
