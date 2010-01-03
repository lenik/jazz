package net.bodz.bas.collection.transform;

public interface ITransformer<S, T> {

    Class<S> getUntransformedType();

    Class<T> getTransformedType();

    T transform(S source);

    S untransform(T transformed);

}
