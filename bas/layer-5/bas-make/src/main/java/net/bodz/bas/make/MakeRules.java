package net.bodz.bas.make;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.IMakeable0;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.make.fn.IMakeable2;
import net.bodz.bas.make.fn.SimpleMakeRule0;
import net.bodz.bas.make.fn.SimpleMakeRule1;
import net.bodz.bas.make.fn.SimpleMakeRule2;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class MakeRules {

    public static <T extends IKeyData<?, ?>> //
    SimpleMakeRule<T>//
    toRule(IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        return SimpleMakeRule.<T>builder()//
                .input(inputs)//
                .fn(fn).build();
    }

    public static <T extends IKeyData<TK, TT>, TK, TT>//
    SimpleMakeRule0<T, TK, TT> //
    toRule(@NotNull IMakeable0<TT> fn) {
        return SimpleMakeRule0.<T, TK, TT>builder() //
                .fn(fn).build();
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    SimpleMakeRule1<T, TK, TT, U, UK, UT>//
    toRule(@NotNull T target, U input1, @NotNull IMakeable1<TT, UT> fn) {
        return SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                .input(input1) //
                .fn(fn).build();
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    SimpleMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> //
    toRule(@NotNull T target, U input1, V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        return SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder().input(input1, input2) //
                .fn(fn).build();
    }

    // rules: key pattern

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    SimpleKeyPatternMakeRule<Tp, Param, K, T, TT> //
    toPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKeys<Param, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        return SimpleKeyPatternMakeRule.<Tp, Param, K, T, TT>builder()//
                .pattern(pattern) //
                .input(inputss)//
                .fn(fn).build();
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    SimpleKeyPatternMakeRule0<Tp, Param, K, T, TT> //
    toPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        return SimpleKeyPatternMakeRule0.<Tp, Param, K, T, TT>builder()//
                .pattern(pattern) //
                .fn(fn).build();
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT> //
    SimpleKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT>//
    toPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        return SimpleKeyPatternMakeRule1.<Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                .pattern(pattern) //
                .input(input1s)//
                .fn(fn).build();
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT> //
    SimpleKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> //
    toPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        return SimpleKeyPatternMakeRule2.<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                .pattern(pattern) //
                .input(input1s, input2s)//
                .fn(fn).build();
    }

    // rules: data typed key pattern

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    SimpleDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> //
    toPatternRule(@NotNull Tp pattern, @NotNull IDataTypedParameterizedKeys<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        return SimpleDataTypedKeyPatternMakeRule.<Tp, Param, K, T, TT>builder()//
                .pattern(pattern) //
                .input(inputss)//
                .fn(fn).build();
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    SimpleDataTypedKeyPatternMakeRule0<Tp, Param, K, T, TT> //
    toPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        return SimpleDataTypedKeyPatternMakeRule0.<Tp, Param, K, T, TT>builder()//
                .pattern(pattern) //
                .fn(fn).build();
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT> //
    SimpleDataTypedKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT>//
    toPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        return SimpleDataTypedKeyPatternMakeRule1.<Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                .pattern(pattern) //
                .input(input1s)//
                .fn(fn).build();
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT> //
    SimpleDataTypedKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> //
    toPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        return SimpleDataTypedKeyPatternMakeRule2.<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                .pattern(pattern) //
                .input(input1s, input2s)//
                .fn(fn).build();
    }

}
