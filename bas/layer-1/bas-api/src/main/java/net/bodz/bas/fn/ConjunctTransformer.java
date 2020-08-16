package net.bodz.bas.fn;

import net.bodz.bas.err.TransformException;

public class ConjunctTransformer<S, T>
        extends AbstractTransformer<S, T> {

    ITransformer<S, ?> first;
    ITransformer<Object, T> second;

    @SuppressWarnings("unchecked")
    public <X> ConjunctTransformer(ITransformer<S, ? extends X> first, ITransformer<? super X, T> second) {
        this.first = first;
        this.second = (ITransformer<Object, T>) second;
    }

    @Override
    public T transform(S input)
            throws TransformException {
        Object tmp = first.transform(input);
        T output = second.transform(tmp);
        return output;
    }

}
