package net.bodz.bas.t.transform;

import net.bodz.bas.model.IBidiTransformer;

public interface ElTransformer<S, T>
        extends IBidiTransformer<S, T> {

    Class<S> getUntransformedType();

    Class<T> getTransformedType();

}
