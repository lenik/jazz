package net.bodz.bas.collection.transform;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.collection.util.CollectionToString;

public class UntransformCollection<S, T>
        implements Collection<S> {

    private final ElTransformer<S, T> transformer;
    // private final Class<S> S_TYPE;
    // private final Class<T> T_TYPE;
    private final Collection<T> transformedCollection;

    public UntransformCollection(ElTransformer<S, T> transformer, Collection<T> transformedCollection) {
        if (transformer == null)
            throw new NullPointerException("transformer");
        if (transformedCollection == null)
            throw new NullPointerException("transformedCollection");
        this.transformer = transformer;
        this.transformedCollection = transformedCollection;
        // S_TYPE = transformer.getUntransformedType();
        // T_TYPE = transformer.getTransformedType();
    }

    public void clear() {
        transformedCollection.clear();
    }

    public boolean isEmpty() {
        return transformedCollection.isEmpty();
    }

    public int size() {
        return transformedCollection.size();
    }

    public boolean add(S source) {
        T transformed = transformer.transform(source);
        return transformedCollection.add(transformed);
    }

    public boolean contains(Object o) {
        // if (!S_TYPE.isInstance(o))
        // return false;
        // S source = S_TYPE.cast(o);
        @SuppressWarnings("unchecked")
        S source = (S) o;
        T transformed = transformer.transform(source);
        return transformedCollection.contains(transformed);
    }

    public boolean remove(Object o) {
        // if (!S_TYPE.isInstance(o))
        // return false;
        // S source = S_TYPE.cast(o);
        @SuppressWarnings("unchecked")
        S source = (S) o;
        T transformed = transformer.transform(source);
        return transformedCollection.remove(transformed);
    }

    public boolean addAll(Collection<? extends S> c) {
        boolean changed = false;
        for (S source : c) {
            T transformed = transformer.transform(source);
            changed |= transformedCollection.add(transformed);
        }
        return changed;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            // if (!S_TYPE.isInstance(e))
            // return false;
            // S source = S_TYPE.cast(e);
            @SuppressWarnings("unchecked")
            S source = (S) o;
            T transformed = transformer.transform(source);
            if (!transformedCollection.contains(transformed))
                return false;
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            // if (!S_TYPE.isInstance(o))
            // continue;
            // S source = S_TYPE.cast(o);
            @SuppressWarnings("unchecked")
            S source = (S) o;
            T transformed = transformer.transform(source);
            changed |= transformedCollection.remove(transformed);
        }
        return changed;
    }

    public boolean retainAll(Collection<?> c) {
        List<T> removeList = new ArrayList<T>(transformedCollection.size());
        for (T transformed : transformedCollection) {
            S source = transformer.untransform(transformed);
            if (!c.contains(source))
                removeList.add(transformed);
        }
        return removeAll(removeList);
    }

    @Override
    public Iterator<S> iterator() {
        return new UntransformIterator<S, T>(transformer, transformedCollection.iterator());
    }

    @Override
    public Object[] toArray() {
        Object[] transformedArray = transformedCollection.toArray();
        Object[] sourceArray = new Object[transformedArray.length];
        for (int i = 0; i < transformedArray.length; i++) {
            @SuppressWarnings("unchecked")
            T transformed = (T) transformedArray[i];
            S source = transformer.untransform(transformed);
            sourceArray[i] = source;
        }
        return sourceArray;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <_S> _S[] toArray(_S[] a) {
        if (a == null)
            throw new NullPointerException("a");

        Class<?> _S_TYPE = a.getClass().getComponentType();

        Object[] _t = new Object[a.length];
        Object[] transformedArray = transformedCollection.toArray(_t);

        _S[] sourceArray;
        int size;
        if (transformedArray == _t) {
            sourceArray = a;
            // size = a.length;
            size = transformedCollection.size(); // SLOW.
            assert a.length >= size;
        } else {
            sourceArray = (_S[]) Array.newInstance(_S_TYPE, transformedArray.length);
            size = transformedArray.length;
        }

        for (int i = 0; i < size; i++) {
            // T transformed = T_TYPE.cast(transformedArray[i]);
            T transformed = (T) transformedArray[i];
            S source = transformer.untransform(transformed);
            // Array.set(sourceArray, i, source);
            sourceArray[i] = (_S) source;
        }
        return (_S[]) sourceArray;
    }

    public boolean equals(Object o) {
        return transformedCollection.equals(o);
    }

    public int hashCode() {
        int hash = 0x61586a4a;
        hash ^= transformer.hashCode() * 0x5597e68a;
        hash ^= transformedCollection.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return CollectionToString.toString(transformedCollection);
    }

}
