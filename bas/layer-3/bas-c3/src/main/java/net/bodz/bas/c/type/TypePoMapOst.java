package net.bodz.bas.c.type;

import net.bodz.bas.c.primitive.IntMath;

public class TypePoMapOst<V>
        extends TypePoMap<TypePoMap<V>> {

    private static final long serialVersionUID = 1L;

    protected TypePoMap<V> create() {
        return new TypePoMap<V>();
    }

    public V get(Class<?> outerKey, Class<?> innerKey) {
        TypePoMap<V> inner = super.get(outerKey);
        if (inner == null)
            return null;
        return inner.get(innerKey);
    }

    public TypePoMap<V> getOrCreate(Class<?> key) {
        TypePoMap<V> inner = super.get(key);
        if (inner == null) {
            inner = create();
            super.put(key, inner);
        }
        return inner;
    }

    public V put(Class<?> outerKey, Class<?> innerKey, V value) {
        TypePoMap<V> inner = super.get(outerKey);
        if (inner != null)
            return inner.put(innerKey, value);

        inner = create();
        super.put(outerKey, inner);
        return inner.put(innerKey, value);
    }

    public V remove(Class<?> outerKey, Class<?> innerKey) {
        TypePoMap<V> inner = super.get(outerKey);
        if (inner == null)
            return null;
        return inner.remove(innerKey);
    }

    public int sizeAll() {
        return IntMath.toInt(_sizeAll());
    }

    long _sizeAll() {
        long all = 0;
        for (TypePoMap<V> inner : values())
            all += inner.size();
        return all;
    }

}
