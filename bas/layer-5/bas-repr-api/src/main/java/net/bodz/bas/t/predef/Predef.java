package net.bodz.bas.t.predef;

import java.io.ObjectStreamException;
import java.io.Serializable;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.decl.Stop;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

@IndexedType(publishDir = PublishDir.features)
@Stop
public abstract class Predef<self_t extends Predef<self_t, K>, K extends Comparable<K>>
        extends XjdocObject
        implements Serializable, Comparable<self_t> {

    private static final long serialVersionUID = 1L;

    protected PredefMetadata<self_t, K> metadata;
    protected final K key;
    protected final String name;

    public Predef(K key, String name) {
        this(key, name, null);
    }

    public Predef(K key, String name, PredefMetadata<self_t, K> metadata) {
        if (key == null)
            throw new NullPointerException("key");
        if (name == null)
            throw new NullPointerException("name");
        this.key = key;
        this.name = name;

        if (metadata == null) {
            Class<self_t> type = (Class<self_t>) getClass();
            metadata = PredefMetadata.forClass(type);
        }

        @SuppressWarnings("unchecked")
        self_t self = (self_t) this;
        metadata.addValue(self);
        this.metadata = metadata;
    }

    public K getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    public PredefMetadata<? extends self_t, K> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(self_t o) {
        if (o == null)
            return 1;
        K k2 = o.getKey();
        return key.compareTo(k2);
    }

    self_t readResolve()
            throws ObjectStreamException {
        return getMetadata().ofKey(key);
    }

}
