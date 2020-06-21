package net.bodz.bas.fn;

import net.bodz.bas.err.TransformException;

public class ConjunctTransformer<S, T>
        extends AbstractTransformer<S, T> {

    ITransformer<S, ?> a;
    ITransformer<Object, T> b;

    @SuppressWarnings("unchecked")
    public <X> ConjunctTransformer(ITransformer<S, ? extends X> a, ITransformer<? super X, T> b) {
        this.a = a;
        this.b = (ITransformer<Object, T>) b;
    }

    @Override
    public T transform(S input)
            throws TransformException {
        Object tmp = a.transform(input);
        T output = b.transform(tmp);
        return output;
    }

}
