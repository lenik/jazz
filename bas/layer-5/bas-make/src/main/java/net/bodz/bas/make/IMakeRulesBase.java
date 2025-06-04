package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKey;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTarget2KeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTarget2KeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedTarget2KeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.ITarget2KeyPattern;
import net.bodz.bas.make.pattern.key.ITarget2KeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleTarget2KeyPatternMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRulesBase {

    boolean canMake(@NotNull IKeyData<?, ?> target, @NotNull IDataBinding binding)
            throws CompileException;

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    // rules: exact match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(T target);

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull T target, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull T target, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(target, //
                SimpleMakeRule.<T, TK, TT, T>builder() //
                        .input(inputs) //
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> MakeRuleBuilder<T, T, TK, TT> matchExact(T target) {
        return new MakeRuleBuilder<>(target, this::addRule);
    }

    // rules: key match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyRules(@NotNull TK key);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyRule(@NotNull TK key, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyRule(key, //
                SimpleMakeRule.<T, TK, TT, TK>builder() //
                        .input(inputs) //
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> MakeRuleBuilder<TK, T, TK, TT> matchKey(@NotNull TK key) {
        return new MakeRuleBuilder<>(key, this::addKeyRule);
    }

    // rules: key type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Class<TK> keyType);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule.<T, TK, TT, TK>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> MakeRuleBuilder<Class<TK>, T, TK, TT> matchKeyType(@NotNull Class<TK> keyType) {
        return new MakeRuleBuilder<>(keyType, this::addKeyTypeRule);
    }

    // rules: data type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TT> dataType);

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT>//
    void addRule(@NotNull Class<TT> dataType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(dataType, //
                SimpleMakeRule.<T, TK, TT, Class<TT>>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> MakeRuleBuilder<Class<TT>, T, TK, TT> matchDataType(@NotNull Class<TT> dataType) {
        return new MakeRuleBuilder<>(dataType, this::addRule);
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
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
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
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedParameterizedKey<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule.<Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    // rules: target-to-key pattern

    @NotNull
    <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> //
    getPatternRules(ITarget2KeyPattern<?, ?, ?, ?> pattern);

    <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> rule);

    default <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleTarget2KeyPatternMakeRule.<Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    // rules: data typed target-to-key pattern

    @NotNull
    <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> //
    getPatternRules(IDataTypedTarget2KeyPattern<?, ?, ?, ?> pattern);

    <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> rule);

    default <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedParameterizedKey<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedTarget2KeyPatternMakeRule.<Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

}
