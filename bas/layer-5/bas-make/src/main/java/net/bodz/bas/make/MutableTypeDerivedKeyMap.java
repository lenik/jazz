package net.bodz.bas.make;

import java.time.ZonedDateTime;
import java.util.Map;

import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyMap<K, EK, EV>
        implements ITypeDerivedKeyMap<K, EK, EV>,
                   INamed {

    final Class<EK> elementKeyType;
    final Class<EV> elementValueType;
    final Class<K> derivedKeyType;

    String name;
    MapKey<K, EK, EV> key;
    K derivedKey;
    Map<EK, EV> data;

    ZonedDateTime lastModified;

    public MutableTypeDerivedKeyMap(@NotNull Class<EK> elementKeyType, @NotNull Class<EV> elementValueType, @NotNull K derivedKey, Map<EK, EV> data) {
        this.elementKeyType = elementKeyType;
        this.elementValueType = elementValueType;

        @SuppressWarnings("unchecked")
        Class<K> keyClass = (Class<K>) derivedKey.getClass();
        this.derivedKeyType = keyClass;

        this.derivedKey = derivedKey;
        this.key = createKey(derivedKey);
        this.data = data;
    }

    public MutableTypeDerivedKeyMap(@NotNull Class<EK> elementKeyType, @NotNull Class<EV> elementValueType, @NotNull Class<K> derivedKeyType, @NotNull K derivedKey, Map<EK, EV> data) {
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

    protected MapKey<K, EK, EV> createKey(K derivedKey) {
        return new MapKey<>(elementKeyType, elementValueType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<MapKey<K, EK, EV>> getKeyType() {
        return (Class<MapKey<K, EK, EV>>) (Class<?>) MapKey.class;
    }

    @NotNull
    @Override
    public MapKey<K, EK, EV> getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<Map<EK, EV>> getDataType() {
        return (Class<Map<EK, EV>>) (Class<?>) Map.class;
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
    public Class<EK> getElementKeyType() {
        return elementKeyType;
    }

    @NotNull
    @Override
    public Class<EV> getElementValueType() {
        return elementValueType;
    }

    @NotNull
    @Override
    public Class<K> getDerivedKeyType() {
        return derivedKeyType;
    }

    @NotNull
    @Override
    public K getDerivedKey() {
        return derivedKey;
    }

}
