package net.bodz.bas.collection.transform;

import net.bodz.bas.model.IBidiTransformer;

public interface ElTransformer<S, T>
        extends IBidiTransformer<S, T> {

    Class<S> getUntransformedType();

    Class<T> getTransformedType();

}
