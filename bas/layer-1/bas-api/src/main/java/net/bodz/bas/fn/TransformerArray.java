package net.bodz.bas.fn;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.TransformException;

public class TransformerArray<T>
        extends AbstractTransformer<T, T> {

    List<? extends ITransformer<? super T, ? extends T>> list;

    @SuppressWarnings("unchecked")
    public TransformerArray(ITransformer<? super T, ? extends T>... array) {
        this.list = Arrays.asList(array);
    }

    public TransformerArray(List<? extends ITransformer<? super T, ? extends T>> list) {
        this.list = list;
    }

    @Override
    public T transform(T input)
            throws TransformException {
        for (ITransformer<? super T, ? extends T> transformer : list) {
            input = transformer.transform(input);
        }
        return input;
    }

}
