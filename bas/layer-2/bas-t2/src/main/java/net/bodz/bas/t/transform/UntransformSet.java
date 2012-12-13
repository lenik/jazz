package net.bodz.bas.t.transform;

import java.util.Collection;
import java.util.Set;

public class UntransformSet<S, T>
        extends UntransformCollection<S, T>
        implements Set<S> {

    public UntransformSet(ElTransformer<S, T> transformer, Collection<T> transformedCollection) {
        super(transformer, transformedCollection);
    }

}
