package net.bodz.bas.fn;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.TransformException;

public class TransformerArray<T>
        extends AbstractTransformer<T, T> {

    List<ITransformer<T, T>> list;

    public TransformerArray(ITransformer<T, T>... array) {
        this.list = Arrays.asList(array);
    }

    public TransformerArray(List<ITransformer<T, T>> list) {
        this.list = list;
    }

    @Override
    public T transform(T input)
            throws TransformException {
        for (ITransformer<T, T> transformer : list) {
            input = transformer.transform(input);
        }
        return input;
    }

}
