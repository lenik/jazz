package net.bodz.bas.fn;

import java.util.function.Function;

public class ConjunctTransformer<S, T>
        implements
            Function<S, T> {

    Function<S, ?> first;
    Function<Object, T> second;

    @SuppressWarnings("unchecked")
    public <X> ConjunctTransformer(Function<S, ? extends X> first, Function<? super X, T> second) {
        this.first = first;
        this.second = (Function<Object, T>) second;
    }

    @Override
    public T apply(S input) {
        Object tmp = first.apply(input);
        T output = second.apply(tmp);
        return output;
    }

}
