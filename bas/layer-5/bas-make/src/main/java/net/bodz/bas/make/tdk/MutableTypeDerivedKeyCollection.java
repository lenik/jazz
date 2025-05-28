package net.bodz.bas.make.tdk;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.make.IKeyDataBuilder;
import net.bodz.bas.make.util.INamed;
import net.bodz.bas.make.util.ICollectionKey;
import net.bodz.bas.meta.decl.NotNull;

public abstract class MutableTypeDerivedKeyCollection<CK extends ICollectionKey<E, K>, K, C extends Collection<E>, E>
        implements ITypeDerivedKeyCollection<CK, K, C, E>,
                   INamed {

    final Class<? extends E> elementType;
    final Class<? extends K> derivedKeyType;

    String name;
    CK key;
    K derivedKey;
    C data;

    ZonedDateTime lastModified;

    public MutableTypeDerivedKeyCollection(@NotNull Class<? extends E> elementType, @NotNull K derivedKey, C data) {
        this.elementType = elementType;

        @SuppressWarnings("unchecked")
        Class<? extends K> keyClass = (Class<? extends K>) derivedKey.getClass();
        this.derivedKeyType = keyClass;

        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    public MutableTypeDerivedKeyCollection(@NotNull Class<? extends E> elementType, @NotNull Class<? extends K> derivedKeyType, @NotNull K derivedKey, C data) {
        this.elementType = elementType;
        this.derivedKeyType = derivedKeyType;
        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    protected abstract CK createKey(K derivedKey);

    @NotNull
    @Override
    public CK getKey() {
        return key;
    }

    @Override
    public C getData() {
        return data;
    }

    @Override
    public void setData(C data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    @NotNull
    @Override
    public Class<? extends E> getElementType() {
        return elementType;
    }

    @NotNull
    @Override
    public Class<? extends K> getDerivedKeyType() {
        return derivedKeyType;
    }

    @NotNull
    @Override
    public K getDerivedKey() {
        return derivedKey;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        if (data == null)
            return Collections.emptyIterator();
        else
            return data.iterator();
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, CK extends ICollectionKey<E, K>, K, C extends Collection<E>, E>
            implements IKeyDataBuilder<self_t, CK, C> {

        protected Class<E> elementType;
        protected Class<K> derivedKeyType;
        protected K derivedKey;
        protected C data;

        public self_t elementType(Class<E> value) {
            this.elementType = value;
            return (self_t) this;
        }

        public self_t derivedKeyType(Class<K> value) {
            this.derivedKeyType = value;
            return (self_t) this;
        }

        public self_t derivedKey(K value) {
            this.derivedKey = value;
            return (self_t) this;
        }

        @Override
        public self_t key(@NotNull CK key) {
            // nop
            return (self_t) this;
        }

        @Override
        public self_t data(C data) {
            this.data = data;
            return (self_t) this;
        }

    }

}
