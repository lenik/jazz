package net.bodz.bas.make.tdk;

import java.time.ZonedDateTime;
import java.util.Map;

import net.bodz.bas.make.IKeyDataBuilder;
import net.bodz.bas.make.util.INamed;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyMap<K, EK, EV>
        implements ITypeDerivedKeyMap<K, EK, EV>,
                   INamed {

    final Class<? extends EK> elementKeyType;
    final Class<? extends EV> elementValueType;
    final Class<? extends K> derivedKeyType;

    String name;
    MapKey<EK, EV, K> key;
    K derivedKey;
    Map<EK, EV> data;

    ZonedDateTime lastModified;

    public MutableTypeDerivedKeyMap(@NotNull Class<? extends EK> elementKeyType, @NotNull Class<? extends EV> elementValueType, @NotNull K derivedKey) {
        this(elementKeyType, elementValueType, derivedKey, null);
    }

    public MutableTypeDerivedKeyMap(@NotNull Class<? extends EK> elementKeyType, @NotNull Class<? extends EV> elementValueType, @NotNull K derivedKey, Map<EK, EV> data) {
        this.elementKeyType = elementKeyType;
        this.elementValueType = elementValueType;

        @SuppressWarnings("unchecked")
        Class<? extends K> keyClass = (Class<? extends K>) derivedKey.getClass();
        this.derivedKeyType = keyClass;

        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    public MutableTypeDerivedKeyMap(@NotNull Class<? extends EK> elementKeyType, @NotNull Class<? extends EV> elementValueType, @NotNull Class<? extends K> derivedKeyType, @NotNull K derivedKey, Map<EK, EV> data) {
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

    protected MapKey<EK, EV, K> createKey(K derivedKey) {
        return new MapKey<>(elementKeyType, elementValueType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<MapKey<EK, EV, K>> getKeyType() {
        return (Class<MapKey<EK, EV, K>>) (Class<?>) MapKey.class;
    }

    @NotNull
    @Override
    public MapKey<EK, EV, K> getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends Map<EK, EV>> getDataType() {
        return (Class<? extends Map<EK, EV>>) (Class<?>) Map.class;
    }

    @Override
    public Map<EK, EV> getData() {
        return data;
    }

    @Override
    public void setData(Map<EK, EV> data) {
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
    public Class<? extends EV> getElementValueType() {
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

    public static <E, EK, EV> Builder<E, EK, EV> builder() {
        return new Builder<>();
    }

    public static class Builder<K, EK, EV>
            implements IKeyDataBuilder<Builder<K, EK, EV>, MapKey<EK, EV, K>, Map<EK, EV>> {

        Class<? extends EK> elementKeyType;
        Class<? extends EV> elementValueType;
        Class<? extends K> derivedKeyType;

        String name;
        K derivedKey;
        Map<EK, EV> data;

        public Builder<K, EK, EV> elementKeyType(@NotNull Class<? extends EK> value) {
            this.elementKeyType = value;
            return this;
        }

        public Builder<K, EK, EV> elementValueType(@NotNull Class<? extends EV> value) {
            this.elementValueType = value;
            return this;
        }

        public Builder<K, EK, EV> derivedKeyType(@NotNull Class<? extends K> value) {
            this.derivedKeyType = value;
            return this;
        }

        public Builder<K, EK, EV> name(String value) {
            this.name = value;
            return this;
        }

        public Builder<K, EK, EV> derivedKey(@NotNull K value) {
            this.derivedKey = value;
            return this;
        }

        @Override
        public Builder<K, EK, EV> key(@NotNull MapKey<EK, EV, K> key) {
            // nop
            return this;
        }

        @Override
        public Builder<K, EK, EV> data(Map<EK, EV> value) {
            this.data = value;
            return this;
        }

        @Override
        public MutableTypeDerivedKeyMap<K, EK, EV> build() {
            if (elementKeyType == null)
                throw new NullPointerException("elementKeyType");
            if (elementValueType == null)
                throw new NullPointerException("elementValueType");
            if (derivedKeyType == null)
                throw new NullPointerException("derivedKeyType");
            if (derivedKey == null)
                throw new NullPointerException("derivedKey");
            return new MutableTypeDerivedKeyMap<>(elementKeyType, elementValueType, derivedKeyType, derivedKey, data);
        }

    }

}
