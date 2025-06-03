package net.bodz.bas.make.tdk;

import java.time.ZonedDateTime;
import java.util.Map;

import net.bodz.bas.make.IKeyDataBuilder;
import net.bodz.bas.make.util.INamed;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyMap<E, EK, K>
        implements ITypeDerivedKeyMap<E, EK, K>,
                   INamed {

    final Class<? extends EK> elementKeyType;
    final Class<? extends E> elementValueType;
    final Class<? extends K> derivedKeyType;

    String name;
    MapKey<E, EK, K> key;
    K derivedKey;
    Map<EK, E> data;

    ZonedDateTime lastModified;

    public MutableTypeDerivedKeyMap(//
            @NotNull Class<? extends E> elementValueType, //
            @NotNull Class<? extends EK> elementKeyType, //
            @NotNull K derivedKey) {
        this(elementValueType, elementKeyType, derivedKey, null);
    }

    public MutableTypeDerivedKeyMap(//
            @NotNull Class<? extends E> elementValueType, //
            @NotNull Class<? extends EK> elementKeyType, //
            @NotNull K derivedKey, //
            Map<EK, E> data) {
        this.elementValueType = elementValueType;
        this.elementKeyType = elementKeyType;

        @SuppressWarnings("unchecked")
        Class<? extends K> keyClass = (Class<? extends K>) derivedKey.getClass();
        this.derivedKeyType = keyClass;

        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    public MutableTypeDerivedKeyMap(//
            @NotNull Class<? extends E> elementValueType, //
            @NotNull Class<? extends EK> elementKeyType, //
            @NotNull Class<? extends K> derivedKeyType, //
            @NotNull K derivedKey, //
            Map<EK, E> data) {
        this.elementKeyType = elementKeyType;
        this.elementValueType = elementValueType;
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

    protected MapKey<E, EK, K> createKey(K derivedKey) {
        return new MapKey<>(elementValueType, elementKeyType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<MapKey<E, EK, K>> getKeyType() {
        return (Class<MapKey<E, EK, K>>) (Class<?>) MapKey.class;
    }

    @NotNull
    @Override
    public MapKey<E, EK, K> getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends Map<EK, E>> getDataType() {
        return (Class<? extends Map<EK, E>>) (Class<?>) Map.class;
    }

    @Override
    public Map<EK, E> getData() {
        return data;
    }

    @Override
    public void setData(Map<EK, E> data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    @NotNull
    @Override
    public Class<? extends EK> getElementKeyType() {
        return elementKeyType;
    }

    @NotNull
    @Override
    public Class<? extends E> getElementValueType() {
        return elementValueType;
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

    public static <E, EK, K> Builder<E, EK, K> builder() {
        return new Builder<>();
    }

    public static class Builder<E, EK, K>
            implements IKeyDataBuilder<Builder<E, EK, K>, MapKey<E, EK, K>, Map<EK, E>> {

        Class<? extends E> elementValueType;
        Class<? extends EK> elementKeyType;
        Class<? extends K> derivedKeyType;

        String name;
        K derivedKey;
        Map<EK, E> data;

        public Builder<E, EK, K> elementValueType(@NotNull Class<? extends E> value) {
            this.elementValueType = value;
            return this;
        }

        public Builder<E, EK, K> elementKeyType(@NotNull Class<? extends EK> value) {
            this.elementKeyType = value;
            return this;
        }

        public Builder<E, EK, K> derivedKeyType(@NotNull Class<? extends K> value) {
            this.derivedKeyType = value;
            return this;
        }

        public Builder<E, EK, K> name(String value) {
            this.name = value;
            return this;
        }

        public Builder<E, EK, K> derivedKey(@NotNull K value) {
            this.derivedKey = value;
            return this;
        }

        @Override
        public Builder<E, EK, K> key(@NotNull MapKey<E, EK, K> key) {
            // nop
            return this;
        }

        @Override
        public Builder<E, EK, K> data(Map<EK, E> value) {
            this.data = value;
            return this;
        }

        @Override
        public MutableTypeDerivedKeyMap<E, EK, K> build() {
            if (elementValueType == null)
                throw new NullPointerException("elementValueType");
            if (elementKeyType == null)
                throw new NullPointerException("elementKeyType");
            if (derivedKeyType == null)
                throw new NullPointerException("derivedKeyType");
            if (derivedKey == null)
                throw new NullPointerException("derivedKey");
            return new MutableTypeDerivedKeyMap<>(elementValueType, elementKeyType, derivedKeyType, derivedKey, data);
        }

    }

}
