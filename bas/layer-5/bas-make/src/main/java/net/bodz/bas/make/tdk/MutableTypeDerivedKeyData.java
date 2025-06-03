package net.bodz.bas.make.tdk;

import java.time.ZonedDateTime;
import java.util.Objects;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyData<E, K, T>
        implements IKeyData<ITypeDerivedKey<E, K>, T>,
                   ITypeDerivedKey<E, K> {

    Class<E> derivedFromType;
    final ITypeDerivedKey<E, K> key = new TDKWrapper<>(this);

    Class<? extends K> derivedKeyType;
    K derivedKey;

    Class<? extends T> dataType;
    T data;

    ZonedDateTime lastModified;

    @NotNull
    @Override
    public Class<? extends E> getDerivedFromType() {
        return derivedFromType;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends ITypeDerivedKey<E, K>> getKeyType() {
        return (Class<? extends ITypeDerivedKey<E, K>>) (Class<?>) ITypeDerivedKey.class;
    }

    @NotNull
    @Override
    public ITypeDerivedKey<E, K> getKey() {
        return key;
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

    public void setDerivedKey(@NotNull K derivedKey) {
        this.derivedKey = derivedKey;
    }

    @NotNull
    @Override
    public Class<? extends T> getDataType() {
        return dataType;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        MutableTypeDerivedKeyData<?, ?, ?> that = (MutableTypeDerivedKeyData<?, ?, ?>) o;
        return Objects.equals(derivedFromType, that.derivedFromType) //
                && Objects.equals(derivedKey, that.derivedKey) //
                && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(derivedFromType, derivedKey, data);
    }

    @Override
    public String toString() {
        String s = getKeyString();
        if (data != null)
            s += " :: " + data;
        return s;
    }

}
