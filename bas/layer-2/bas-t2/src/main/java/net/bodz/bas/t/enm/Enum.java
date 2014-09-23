package net.bodz.bas.t.enm;

import java.io.ObjectStreamException;
import java.io.Serializable;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(publishDir = "META-INF/enums")
public abstract class Enum<E extends Enum<E, K>, K extends Comparable<K>>
        // extends AbstractElement
        implements Serializable, Comparable<E> {

    private static final long serialVersionUID = 1L;

    protected EnumMetadata<E, K> metadata;
    protected final K key;
    protected final String name;

    public Enum(K key, String name) {
        this(key, name, null);
    }

    public Enum(K key, String name, EnumMetadata<E, K> metadata) {
        if (key == null)
            throw new NullPointerException("key");
        if (name == null)
            throw new NullPointerException("name");
        this.key = key;
        this.name = name;

        if (metadata == null) {
            Class<E> type = (Class<E>) getClass();
            metadata = EnumMetadata.forClass(type);
        }

        @SuppressWarnings("unchecked") E self = (E) this;
        metadata.addValue(self);
        this.metadata = metadata;
    }

    public K getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public EnumMetadata<? extends E, K> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(E o) {
        if (o == null)
            return 1;
        K k2 = o.getKey();
        return key.compareTo(k2);
    }

    E readResolve()
            throws ObjectStreamException {
        return getMetadata().getLocalValue(key);
    }

}
