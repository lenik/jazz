package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.IMakeable0;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.make.fn.IMakeable2;
import net.bodz.bas.make.fn.IMakeable3;
import net.bodz.bas.make.fn.SimpleMakeRule0;
import net.bodz.bas.make.fn.SimpleMakeRule1;
import net.bodz.bas.make.fn.SimpleMakeRule2;
import net.bodz.bas.make.fn.SimpleMakeRule3;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule3;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule3;
import net.bodz.bas.make.tdk.ITypeDerivedKeyList;
import net.bodz.bas.make.tdk.ITypeDerivedKeyMap;
import net.bodz.bas.make.tdk.ITypeDerivedKeySet;
import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeSession {

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

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull T target, @NotNull IMakeable0<TT> fn) {
        addRule(target, //
                SimpleMakeRule0.<T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addRule(@NotNull T target, U input1, @NotNull IMakeable1<TT, UT> fn) {
        addRule(target, //
                SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addRule(@NotNull T target, U input1, V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addRule(target, //
                SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT> //
    void addRule(@NotNull T target, U input1, V input2, W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addRule(target, //
                SimpleMakeRule3.<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
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

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyRule(@NotNull TK key, @NotNull IMakeable0<TT> fn) {
        addKeyRule(key, //
                SimpleMakeRule0.<T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addKeyRule(key, //
                SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addKeyRule(key, //
                SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addKeyRule(key, //
                SimpleMakeRule3.<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
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

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeable0<TT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule0.<T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule3.<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
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

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull IMakeable0<TT> fn) {
        addRule(dataType, //
                SimpleMakeRule0.<T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addRule(dataType, //
                SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addRule(dataType, //
                SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addRule(dataType, //
                SimpleMakeRule3.<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
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

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule0.<Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        addPatternRule(pattern,//
                SimpleKeyPatternMakeRule1.<Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule2.<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }


    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            Ws extends IParameterizedKeys<Param, WK>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule3.<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
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

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule0.<Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule1.<Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK,//
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule2.<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK,//
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule3.<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
                        .fn(fn).build());
    }

}
