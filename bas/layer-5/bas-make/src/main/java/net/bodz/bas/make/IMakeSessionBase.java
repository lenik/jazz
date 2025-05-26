package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.tdk.ITypeDerivedKeyList;
import net.bodz.bas.make.tdk.ITypeDerivedKeyMap;
import net.bodz.bas.make.tdk.ITypeDerivedKeySet;
import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeSessionBase {

    <T extends IKeyData<?, ?>> boolean canMake(@NotNull T target);

    <T extends IKeyData<TK, TT>, TK, TT> void make(T target)
            throws MakeException;

    // data

    void addData(@NotNull IKeyData<?, ?> entry);

    <K> IKeyData<K, ?> getData(@NotNull K key);

    <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataClass);

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

    // rules: exact match

    @NotNull
    <T extends IKeyData<?, ?>> List<IMakeRule<T>> getRules(T target);

    <T extends IKeyData<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<?, ?>> //
    void addRule(@NotNull T target, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(target, //
                SimpleMakeRule.<T>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: key match

    @NotNull
    <T extends IKeyData<TK, ?>, TK> List<IMakeRule<T>> getKeyRules(@NotNull TK key);

    <T extends IKeyData<TK, ?>, TK> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, ?>, TK> //
    void addKeyRule(@NotNull TK key, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyRule(key, //
                SimpleMakeRule.<T>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: key type match

    @NotNull
    <T extends IKeyData<TK, ?>, TK> List<IMakeRule<T>> getKeyTypeRules(@NotNull Class<TK> keyType);

    <T extends IKeyData<TK, ?>, TK> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, ?>, TK> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule.<T>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: data type match

    @NotNull
    <T extends IKeyData<?, TT>, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType);

    <T extends IKeyData<?, TT>, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<?, TT>, TT> //
    void addRule(@NotNull Class<TT> dataType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(dataType, //
                SimpleMakeRule.<T>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: key pattern

    @NotNull
    <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IKeyPatternMakeRule<Tp, Param, K, T, TT>> //
    getPatternRules(IKeyPattern<?, ?> pattern);

    <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IKeyPatternMakeRule<Tp, Param, K, T, TT> rule);

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKeys<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule.<Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    // rules: data-typed key pattern

    @NotNull
    <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> //
    getPatternRules(IDataTypedKeyPattern<?, ?, ?> pattern);

    <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> rule);

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedParameterizedKeys<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule.<Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

}
