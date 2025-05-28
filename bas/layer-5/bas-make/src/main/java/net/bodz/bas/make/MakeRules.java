package net.bodz.bas.make;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.strategy.DataTypeMatch;
import net.bodz.bas.make.strategy.DataTypedKeyPatternMatch;
import net.bodz.bas.make.strategy.ExactMatch;
import net.bodz.bas.make.strategy.IMakeStrategy;
import net.bodz.bas.make.strategy.KeyMatch;
import net.bodz.bas.make.strategy.KeyPatternMatch;
import net.bodz.bas.make.strategy.KeyTypeMatch;
import net.bodz.bas.meta.decl.NotNull;

public class MakeRules
        implements IMakeRules {

    public final ExactMatch exactMatch = new ExactMatch();
    public final KeyMatch keyMatch = new KeyMatch();
    public final KeyTypeMatch keyTypeMatch = new KeyTypeMatch();
    public final DataTypeMatch dataTypeMatch = new DataTypeMatch();
    public final KeyPatternMatch keyPatternMatch = new KeyPatternMatch();
    public final DataTypedKeyPatternMatch dataTypedKeyPatternMatch = new DataTypedKeyPatternMatch();

    IMakeStrategy[] strategies = { //
            exactMatch, //
            keyMatch, //
            keyTypeMatch, //
            dataTypeMatch, //
            keyPatternMatch, //
            dataTypedKeyPatternMatch, //
    };

    // rules: exact match

    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getRules(T target) {
        return exactMatch.getRules(target);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule) {
        exactMatch.addRule(target, rule);
    }

    // rules: key match

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getKeyRules(@NotNull TK key) {
        return keyMatch.getRules(key);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T> rule) {
        keyMatch.addRule(key, rule);
    }

    // rules: key type

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getKeyTypeRules(@NotNull Class<TK> keyType) {
        return keyTypeMatch.getRules(keyType);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule) {
        keyTypeMatch.addRule(keyType, rule);
    }

    // rules: data type

    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType) {
        return dataTypeMatch.getRules(dataType);
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule) {
        dataTypeMatch.addRule(dataType, rule);
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
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IMakeStrategy strategy : strategies) {
            IMakeRule<T> rule = strategy.makeDefaultRule(target, binding);
            if (rule != null)
                return rule;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T>> list = new ArrayList<>();
        for (IMakeStrategy strategy : strategies) {
            for (IMakeRule<? extends IKeyData<?, ?>> rule : strategy.makeRules(target, binding)) {
                list.add((IMakeRule<T>) (IMakeRule<?>) rule);
            }
        }
        return list;
    }

}