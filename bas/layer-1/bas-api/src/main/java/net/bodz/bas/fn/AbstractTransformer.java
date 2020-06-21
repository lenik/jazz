package net.bodz.bas.fn;

public abstract class AbstractTransformer<S, T>
        implements ITransformer<S, T> {

    public <Y> ITransformer<S, Y> join(ITransformer<? super T, Y> o) {
        return new ConjunctTransformer<>(this, o);
    }

}
