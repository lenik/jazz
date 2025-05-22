package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeSession {

    void addData(@NotNull IKeyData<?, ?> entry);

    <K> IKeyData<K, ?> getData(@NotNull K key);

    <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataClass);

    default <K, E> ITypeDerivedKeyList<K, E> getListData(@NotNull Class<E> elementType, K key) {
        ListKey<K, E> listKey = new ListKey<>(elementType, key);
        IKeyData<ListKey<K, E>, ?> _data = getData(listKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyList<K, E> data = (ITypeDerivedKeyList<K, E>) _data;
        return data;
    }

    default <K, E> ITypeDerivedKeySet<K, E> getSetData(@NotNull Class<E> elementType, K key) {
        SetKey<K, E> setKey = new SetKey<>(elementType, key);
        IKeyData<SetKey<K, E>, ?> _data = getData(setKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeySet<K, E> data = (ITypeDerivedKeySet<K, E>) _data;
        return data;
    }

    default <K, EK, EV> ITypeDerivedKeyMap<K, EK, EV> getSetData(@NotNull Class<EK> elementKeyType, Class<EV> elementValueType, K key) {
        MapKey<K, EK, EV> mapKey = new MapKey<>(elementKeyType, elementValueType, key);
        IKeyData<MapKey<K, EK, EV>, ?> _data = getData(mapKey);
        @SuppressWarnings("unchecked")
        ITypeDerivedKeyMap<K, EK, EV> data = (ITypeDerivedKeyMap<K, EK, EV>) _data;
        return data;
    }

    // rules

    @NotNull
    <T extends IKeyData<?, ?>> List<IMakeRule<T>> getRules(T target);

    <T extends IKeyData<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule);

    <T extends IKeyData<?, ?>> void addRule(@NotNull T target, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn);

    <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addRule(@NotNull T target, U input1, @NotNull MakeFunction1<TT, UT> fn);

    <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addRule(@NotNull T target, U input1, V input2, @NotNull MakeFunction2<TT, UT, VT> fn);

    <T extends IKeyData<TK, TT>, TK, TT> void make(T target)
            throws MakeException;

    // parameterized rules

    @NotNull
    <T extends IKeyPattern<?, K>, K, D extends IKeyData<K, ?>> //
    List<IPatternMakeRule<T, K, D>> getPatternRules(IKeyPattern<?, ?> pattern);

    <T extends IKeyPattern<?, K>, K, D extends IKeyData<K, ?>> //
    void addPatternRule(@NotNull T pattern, @NotNull IPatternMakeRule<T, K, D> rule);

    //

    <T extends IKeyData<?, ?>> boolean canMake(@NotNull T target);

}
