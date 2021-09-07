package net.bodz.bas.fn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TransformerArray<T>
        implements
            Function<T, T> {

    List<? extends Function<? super T, ? extends T>> list;

    @SuppressWarnings("unchecked")
    public TransformerArray(Function<? super T, ? extends T>... array) {
        this.list = Arrays.asList(array);
    }

    public TransformerArray(List<? extends Function<? super T, ? extends T>> list) {
        this.list = list;
    }

    @Override
    public T apply(T input) {
        for (Function<? super T, ? extends T> transformer : list) {
            input = transformer.apply(input);
        }
        return input;
    }

}
