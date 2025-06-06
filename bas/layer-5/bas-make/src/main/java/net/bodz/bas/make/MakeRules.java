package net.bodz.bas.make;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyExtendsPattern;
import net.bodz.bas.make.pattern.key.IKeyExtendsPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.ITargetTypedKeyPattern;
import net.bodz.bas.make.pattern.key.ITargetTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.target.ITargetPattern;
import net.bodz.bas.make.pattern.target.ITargetPatternMakeRule;
import net.bodz.bas.make.strategy.DataExtendsMatch;
import net.bodz.bas.make.strategy.DataTypeMatch;
import net.bodz.bas.make.strategy.DataTypedKeyPatternMatch;
import net.bodz.bas.make.strategy.ExactMatch;
import net.bodz.bas.make.strategy.IMakeStrategy;
import net.bodz.bas.make.strategy.KeyExtendsMatch;
import net.bodz.bas.make.strategy.KeyExtendsPatternMatch;
import net.bodz.bas.make.strategy.KeyMatch;
import net.bodz.bas.make.strategy.KeyPatternMatch;
import net.bodz.bas.make.strategy.KeyTypeMatch;
import net.bodz.bas.make.strategy.TargetPatternMatch;
import net.bodz.bas.make.strategy.TargetTypedKeyPatternMatch;
import net.bodz.bas.make.type.Extends;
import net.bodz.bas.meta.decl.NotNull;

public class MakeRules
        implements IMakeRules {

    public final ExactMatch exactMatch = new ExactMatch();
    public final KeyMatch keyMatch = new KeyMatch();

    public final KeyTypeMatch keyTypeMatch = new KeyTypeMatch();
    public final DataTypeMatch dataTypeMatch = new DataTypeMatch();

    public final KeyExtendsMatch keyExtendsMatch = new KeyExtendsMatch();
    public final KeyExtendsPatternMatch keyExtendsPatternMatch = new KeyExtendsPatternMatch();

    public final DataExtendsMatch dataExtendsMatch = new DataExtendsMatch();

    public final KeyPatternMatch keyPatternMatch = new KeyPatternMatch();
    public final DataTypedKeyPatternMatch dataTypedKeyPatternMatch = new DataTypedKeyPatternMatch();

    public final TargetTypedKeyPatternMatch targetTypedKeyPatternMatch = new TargetTypedKeyPatternMatch();

    public final TargetPatternMatch targetPatternMatch = new TargetPatternMatch();

    IMakeStrategy[] strategies = { //
            exactMatch, //
            keyMatch, //
            keyTypeMatch, //
            dataTypeMatch, //
            dataExtendsMatch, //
            keyPatternMatch, //
            dataTypedKeyPatternMatch, //
            targetTypedKeyPatternMatch, //
            targetPatternMatch, //
    };

    // rules: exact match

    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(T target) {
        return exactMatch.getRules(target);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull T target, @NotNull IMakeRule<T, TK, TT> rule) {
        exactMatch.addRule(target, rule);
    }

    // rules: key match

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyRules(@NotNull TK key) {
        return keyMatch.getRules(key);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T, TK, TT> rule) {
        keyMatch.addRule(key, rule);
    }

    // rules: key type

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Class<TK> keyType) {
        return keyTypeMatch.getRules(keyType);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T, TK, TT> rule) {
        keyTypeMatch.addRule(keyType, rule);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getKeyTypeRules(@NotNull Extends dataInterfaces) {
        return keyExtendsMatch.getRules(dataInterfaces);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Extends keyInterfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        keyExtendsMatch.addRule(keyInterfaces, rule);
    }

    @NotNull
    @Override
    public <Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>//
    List<IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> getPatternRules(@NotNull IKeyExtendsPattern<?, K> pattern) {
        Class<?>[] interfaces = pattern.getInterfaces();
        return keyExtendsPatternMatch.getRules(Extends.of(interfaces));
    }

    @Override
    public <Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull IKeyExtendsPattern<Param, K> pattern, @NotNull IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> rule) {
        @SuppressWarnings("unchecked")
        Class<? super K>[] interfaces = (Class<? super K>[]) pattern.getInterfaces();
        keyExtendsPatternMatch.addRule(interfaces, rule);
    }

    // rules: data type

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TT> dataType) {
        return dataTypeMatch.getRules(dataType);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T, TK, TT> rule) {
        dataTypeMatch.addRule(dataType, rule);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Extends dataInterfaces) {
        return dataExtendsMatch.getRules(dataInterfaces);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Extends dataInterfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        dataExtendsMatch.addRule(dataInterfaces, rule);
    }

    // rules: key pattern

    @NotNull
    @Override
    public <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IKeyPatternMakeRule<Tp, Param, K, T, TT>> getPatternRules(IKeyPattern<?, ?> pattern) {
        return keyPatternMatch.getRules(pattern);
    }

    @Override
    public <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IKeyPatternMakeRule<Tp, Param, K, T, TT> rule) {
        keyPatternMatch.addRule(pattern, rule);
    }

    // rules: data typed key pattern

    @NotNull
    @Override
    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> getPatternRules(IDataTypedKeyPattern<?, ?, ?> pattern) {
        return dataTypedKeyPatternMatch.getRules(pattern);
    }

    @Override
    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> rule) {
        dataTypedKeyPatternMatch.addRule(pattern, rule);
    }

    // rules: target typed key pattern

    @Override
    @NotNull
    public <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> getPatternRules(ITargetTypedKeyPattern<?, ?, ?, ?> pattern) {
        return targetTypedKeyPatternMatch.getRules(pattern);
    }

    @Override
    public <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> rule) {
        targetTypedKeyPatternMatch.addRule(pattern, rule);
    }

    // rule: target pattern

    @NotNull
    @Override
    public <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITargetPatternMakeRule<Tp, Param, TK, T, TT>> getPatternRules(ITargetPattern<?, ?, ?, ?> pattern) {
        return targetPatternMatch.getRules(pattern);
    }

    @Override
    public <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull ITargetPatternMakeRule<Tp, Param, TK, T, TT> rule) {
        targetPatternMatch.addRule(pattern, rule);
    }

    // make

    @Override
    public boolean canMake(@NotNull IKeyData<?, ?> target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IMakeStrategy strategy : strategies)
            if (strategy.canMake(target, binding))
                return true;
        return false;
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IMakeStrategy strategy : strategies) {
            IMakeRule<T, TK, TT> rule = strategy.makeDefaultRule(target, binding);
            if (rule != null)
                return rule;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T, TK, TT>> list = new ArrayList<>();
        for (IMakeStrategy strategy : strategies) {
            for (IMakeRule<? extends IKeyData<?, ?>, ?, ?> rule : strategy.makeRules(target, binding)) {
                list.add((IMakeRule<T, TK, TT>) (IMakeRule<?, ?, ?>) rule);
            }
        }
        return list;
    }

}