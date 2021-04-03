package net.bodz.bas.fn;

public abstract class AbstractTransformer<S, T>
        implements
            ITransformer<S, T> {

    // @Override
    // public <Y> ITransformer<S, Y> join(ITransformer<? super T, Y> second) {
    // return new ConjunctTransformer<S, Y>(this, second);
    // }

}
