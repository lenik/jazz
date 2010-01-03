package net.bodz.bas.collection.transform;

import java.util.Collection;
import java.util.Set;

public class UntransformSet<S, T>
        extends UntransformCollection<S, T>
        implements Set<S> {

    public UntransformSet(ITransformer<S, T> transformer, Collection<T> transformedCollection) {
        super(transformer, transformedCollection);
    }

}
