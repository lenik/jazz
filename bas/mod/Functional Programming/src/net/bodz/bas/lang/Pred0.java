package net.bodz.bas.lang;

public abstract class Pred0 implements Func0<Boolean> {

    @Override
    public final Boolean eval() {
        return test();
    }

    public abstract boolean test();

}
