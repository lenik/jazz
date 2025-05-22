package net.bodz.bas.make;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.make.util.ICollectionKey;
import net.bodz.bas.meta.decl.NotNull;

public abstract class MutableTypeDerivedKeyCollection<CK extends ICollectionKey<K, E>, K, C extends Collection<E>, E>
        implements ITypeDerivedKeyCollection<CK, K, C, E>,
                   INamed {

    final Class<E> elementType;
    final Class<K> derivedKeyType;

    String name;
    CK key;
    K derivedKey;
    C data;

    ZonedDateTime lastModified;

    public MutableTypeDerivedKeyCollection(@NotNull Class<E> elementType, @NotNull K derivedKey, C data) {
        this.elementType = elementType;

        @SuppressWarnings("unchecked")
        Class<K> keyClass = (Class<K>) derivedKey.getClass();
        this.derivedKeyType = keyClass;

        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    public MutableTypeDerivedKeyCollection(@NotNull Class<E> elementType, @NotNull Class<K> derivedKeyType, @NotNull K derivedKey, C data) {
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
    public Class<E> getElementType() {
        return elementType;
    }

    @Override
    public Class<K> getDerivedKeyType() {
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

}
