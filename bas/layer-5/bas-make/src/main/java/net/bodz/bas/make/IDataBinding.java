package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.tdk.ITypeDerivedKeyList;
import net.bodz.bas.make.tdk.ITypeDerivedKeyMap;
import net.bodz.bas.make.tdk.ITypeDerivedKeySet;
import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public interface IDataBinding {

    void addData(@NotNull IKeyData<?, ?> keyData);

    <K> IKeyData<K, ?> getData(@NotNull K key);

    default <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataType) {
        return findData(dataType, false);
    }

    <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataType, boolean join);

    default <K> List<IKeyData<K, ?>> findDataByKeyType(@NotNull Class<K> keyType) {
        return findDataByKeyType(keyType, false);
    }

    <K> List<IKeyData<K, ?>> findDataByKeyType(@NotNull Class<K> keyType, boolean join);

    <K, T> List<IKeyData<K, T>> resolve(@NotNull IDataTypedKey<K, T> dataTypedKey);

    @NotNull
    IKeyData<?, ?>[] resolve(@NotNull IDataTypedKey<?, ?>... inputKeys);

    default <K, E> ITypeDerivedKeyList<K, E> getListData(@NotNull Class<E> elementType, K key) {
        ListKey<E, K> listKey = new ListKey<>(elementType, key);
        IKeyData<ListKey<E, K>, ?> _data = getData(listKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyList<K, E> data = (ITypeDerivedKeyList<K, E>) _data;
        return data;
    }

    default <K, E> ITypeDerivedKeySet<K, E> getSetData(@NotNull Class<E> elementType, K key) {
        SetKey<E, K> setKey = new SetKey<>(elementType, key);
        IKeyData<SetKey<E, K>, ?> _data = getData(setKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeySet<K, E> data = (ITypeDerivedKeySet<K, E>) _data;
        return data;
    }

    default <K, EK, EV> ITypeDerivedKeyMap<K, EK, EV> getSetData(@NotNull Class<EK> elementKeyType, Class<EV> elementValueType, K key) {
        MapKey<EK, EV, K> mapKey = new MapKey<>(elementKeyType, elementValueType, key);
        IKeyData<MapKey<EK, EV, K>, ?> _data = getData(mapKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyMap<K, EK, EV> data = (ITypeDerivedKeyMap<K, EK, EV>) _data;
        return data;
    }

}
