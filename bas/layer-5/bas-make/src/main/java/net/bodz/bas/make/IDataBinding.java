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

    default <E, K> ITypeDerivedKeyList<E, K> getListData(@NotNull Class<E> elementType, K key) {
        ListKey<E, K> listKey = new ListKey<>(elementType, key);
        IKeyData<ListKey<E, K>, ?> _data = getData(listKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyList<E, K> data = (ITypeDerivedKeyList<E, K>) _data;
        return data;
    }

    default <E, K> ITypeDerivedKeySet<E, K> getSetData(@NotNull Class<E> elementType, K key) {
        SetKey<E, K> setKey = new SetKey<>(elementType, key);
        IKeyData<SetKey<E, K>, ?> _data = getData(setKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeySet<E, K> data = (ITypeDerivedKeySet<E, K>) _data;
        return data;
    }

    default <E, EK, K> ITypeDerivedKeyMap<E, EK, K> getMapData(//
            Class<E> elementValueType, //
            @NotNull Class<EK> elementKeyType, //
            K key) {
        MapKey<E, EK, K> mapKey = new MapKey<>(elementValueType, elementKeyType, key);
        IKeyData<MapKey<E, EK, K>, ?> _data = getData(mapKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyMap<E, EK, K> data = (ITypeDerivedKeyMap<E, EK, K>) _data;
        return data;
    }

    default <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataType) {
        return findData(dataType, false);
    }

    <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataType, boolean join);

    default <K> List<IKeyData<K, ?>> findDataByKeyType(@NotNull Class<K> keyType) {
        return findDataByKeyType(keyType, false);
    }

    <K> List<IKeyData<K, ?>> findDataByKeyType(@NotNull Class<K> keyType, boolean join);

    //

    <K, T> List<IKeyData<K, T>> resolve(@NotNull IDataTypedKey<K, T> dataTypedKey);

    @NotNull
    IKeyData<?, ?>[] resolve(@NotNull IDataTypedKey<?, ?>... inputKeys);

}
