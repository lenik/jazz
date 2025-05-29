package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTargetPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTargetPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedTargetPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.ITargetPattern;
import net.bodz.bas.make.pattern.key.ITargetPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleTargetPatternMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRulesBase {

    boolean canMake(@NotNull IKeyData<?, ?> target, @NotNull IDataBinding binding)
            throws CompileException;

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    // rules: exact match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getRules(T target);

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull T target, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(target, //
                SimpleMakeRule.<T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: key match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getKeyRules(@NotNull TK key);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyRule(@NotNull TK key, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyRule(key, //
                SimpleMakeRule.<T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: key type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getKeyTypeRules(@NotNull Class<TK> keyType);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule.<T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    // rules: data type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType);

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule);

    default <T extends IKeyData<TK, TT>, TK, TT>//
    void addRule(@NotNull Class<TT> dataType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(dataType, //
                SimpleMakeRule.<T, TK, TT>builder()//
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

    // rules: target pattern

    @NotNull
    <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITargetPatternMakeRule<Tp, Param, TK, T, TT>> //
    getPatternRules(ITargetPattern<?, ?, ?, ?> pattern);

    <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull ITargetPatternMakeRule<Tp, Param, TK, T, TT> rule);

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKeys<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule.<Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    // rules: data typed target pattern

    @NotNull
    <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT>> //
    getPatternRules(IDataTypedTargetPattern<?, ?, ?, ?> pattern);

    <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> rule);

    default <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedParameterizedKeys<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedTargetPatternMakeRule.<Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

}
