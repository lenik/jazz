package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKey;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyExtendsPattern;
import net.bodz.bas.make.pattern.key.IKeyExtendsPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.ITargetTypedKeyPattern;
import net.bodz.bas.make.pattern.key.ITargetTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyExtendsPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.target.ITargetPattern;
import net.bodz.bas.make.pattern.target.ITargetPatternMakeRule;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule;
import net.bodz.bas.make.type.Extends;
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
                SimpleMakeRule.<T, T, TK, TT>builder() //
                        .input(inputs) //
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> SimpleMakeRule.Dispatcher<T, T, TK, TT> forExact(T target) {
        return new SimpleMakeRule.Dispatcher<>(this::addRule, target);
    }

    // rules: key match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyRules(@NotNull TK key);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyRule(@NotNull TK key, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyRule(key, //
                SimpleMakeRule.<TK, T, TK, TT>builder() //
                        .input(inputs) //
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> SimpleMakeRule.Dispatcher<TK, T, TK, TT> forKey(@NotNull TK key) {
        return new SimpleMakeRule.Dispatcher<>(this::addKeyRule, key);
    }

    // rules: key type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Class<TK> keyType);

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule.<TK, T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> SimpleMakeRule.Dispatcher<Class<TK>, T, TK, TT> //
    forKeyType(@NotNull Class<TK> keyType) {
        return new SimpleMakeRule.Dispatcher<>(this::addKeyTypeRule, keyType);
    }

    // rules: key extends match

    @SuppressWarnings("unchecked")
    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Class<? super TK>... keyInerfaces) {
        return getKeyTypeRules(Extends.of(keyInerfaces));
    }

    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Extends keyInerfaces);

    default <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<? super TK>[] keyInterfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        addKeyTypeRule(Extends.of(keyInterfaces), rule);
    }

    <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Extends keyInterfaces, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<? super TK>[] keyInterfaces, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addKeyTypeRule(keyInterfaces, //
                SimpleMakeRule.<TK, T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @SuppressWarnings("unchecked")
    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> SimpleMakeRule.Dispatcher<Class<? super TK>[], T, TK, TT> //
    forKeyType(@NotNull Class<? super TK>... keyInterfaces) {
        return new SimpleMakeRule.Dispatcher<>(this::addKeyTypeRule, keyInterfaces);
    }

    // rules: key extends pattern match

    @NotNull
    <Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> List<IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> //
    getPatternRules(@NotNull IKeyExtendsPattern<?, K> pattern);

    <Tp extends IKeyExtendsPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull IKeyExtendsPattern<Param, K> pattern, @NotNull IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> rule);

    default <Tp extends IKeyExtendsPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleKeyExtendsPatternMakeRule.<Tp, Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyExtendsPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    SimpleKeyExtendsPatternMakeRule.Dispatcher<Tp, Tp, Param, K, T, TT> forKeys(@NotNull Tp pattern) {
        return new SimpleKeyExtendsPatternMakeRule.Dispatcher<>(this::addPatternRule, pattern, pattern);
    }

    @SuppressWarnings("unchecked")
    default <Tp extends IKeyExtendsPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT, //
            T1> //
    SimpleKeyExtendsPatternMakeRule.Dispatcher<Tp, Tp, Param, K, T, TT> forKeys(@NotNull Class<T1> type1) {
//        return new SimpleKeyExtendsPatternMakeRule.Dispatcher1<>(this::addPatternRule, type1);
        return null;
    }

    // rules: data type match

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TT> dataType);

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT>//
    void addRule(@NotNull Class<TT> dataType, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(dataType, //
                SimpleMakeRule.<Class<TT>, T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> SimpleMakeRule.Dispatcher<Class<TT>, T, TK, TT> forType(@NotNull Class<TT> dataType) {
        return new SimpleMakeRule.Dispatcher<>(this::addRule, dataType);
    }

    // rules: data extends match

    @SuppressWarnings("unchecked")
    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<? super TT>... dataInterfaces) {
        return getRules(Extends.of(dataInterfaces));
    }

    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Extends dataInterfaces);

    default <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<? super TT>[] dataInterfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        addRule(Extends.of(dataInterfaces), rule);
    }

    <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Extends dataInterfaces, @NotNull IMakeRule<T, TK, TT> rule);

    default <T extends IKeyData<TK, TT>, TK, TT>//
    void addRule(@NotNull Class<? super TT>[] dataInterfaces, IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        addRule(dataInterfaces, //
                SimpleMakeRule.<Class<TT>, T, TK, TT>builder()//
                        .input(inputs)//
                        .fn(fn).build());
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> //
    SimpleMakeRule.Dispatcher<Class<? super TT>[], T, TK, TT> forType(@NotNull Class<? super TT>[] dataInterfaces) {
        return new SimpleMakeRule.Dispatcher<>(this::addRule, dataInterfaces);
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
                SimpleKeyPatternMakeRule.<Tp, Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    SimpleKeyPatternMakeRule.Dispatcher<Tp, Tp, Param, K, T, TT> forKeys(@NotNull Tp pattern) {
        return new SimpleKeyPatternMakeRule.Dispatcher<>(this::addPatternRule, pattern, pattern);
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
                SimpleDataTypedKeyPatternMakeRule.<Tp, Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    SimpleDataTypedKeyPatternMakeRule.Dispatcher<Tp, Tp, Param, K, T, TT> forKeysDataTyped(@NotNull Tp pattern) {
        return new SimpleDataTypedKeyPatternMakeRule.Dispatcher<>(this::addPatternRule, pattern, pattern);
    }

    // rules: target-to-key pattern

    @NotNull
    <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> //
    getPatternRules(ITargetTypedKeyPattern<?, ?, ?, ?> pattern);

    <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> rule);

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule.<Tp, Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    SimpleTargetTypedKeyPatternMakeRule.Dispatcher<Tp, Tp, Param, TK, T, TT> forKeysQualified(@NotNull Tp pattern) {
        return new SimpleTargetTypedKeyPatternMakeRule.Dispatcher<>(this::addPatternRule, pattern, pattern);
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
    void addPatternRule(@NotNull Tp pattern, @NotNull IParameterizedTarget<?, ?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule.<Tp, Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .input(inputss)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    SimpleTargetPatternMakeRule.Dispatcher<Tp, Tp, Param, TK, T, TT> forTargets(@NotNull Tp pattern) {
        return new SimpleTargetPatternMakeRule.Dispatcher<>(this::addPatternRule, pattern, pattern);
    }

}
