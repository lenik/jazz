package net.bodz.bas.make;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.make.plan.IMakeNode;
import net.bodz.bas.make.strategy.DataTypeMatch;
import net.bodz.bas.make.strategy.DataTypedKeyPatternMatch;
import net.bodz.bas.make.strategy.ExactMatch;
import net.bodz.bas.make.strategy.KeyMatch;
import net.bodz.bas.make.strategy.KeyPatternMatch;
import net.bodz.bas.make.strategy.KeyTypeMatch;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class MakeSession
        implements IMakeSession {

    // data sources
    Map<Object, IKeyData<?, ?>> keyMap = new HashMap<>();
    ListMap<Class<?>, IKeyData<?, ?>> typeListMap = new ListMap<>();

    public final ExactMatch exactMatch = new ExactMatch();
    public final KeyMatch keyMatch = new KeyMatch();
    public final KeyTypeMatch keyTypeMatch = new KeyTypeMatch();
    public final DataTypeMatch dataTypeMatch = new DataTypeMatch();
    public final KeyPatternMatch keyPatternMatch = new KeyPatternMatch();
    public final DataTypedKeyPatternMatch dataTypedKeyPatternMatch = new DataTypedKeyPatternMatch();

    @Override
    public void addData(@NotNull IKeyData<?, ?> entry) {
        keyMap.put(entry.getKey(), entry);
    }

    @Override
    public <K> IKeyData<K, ?> getData(@NotNull K key) {
        @SuppressWarnings("unchecked")
        IKeyData<K, ?> data = (IKeyData<K, ?>) keyMap.get(key);
        return data;
    }

    @Override
    public <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataClass) {
        @SuppressWarnings("unchecked")
        List<IKeyData<?, T>> list = (List<IKeyData<?, T>>) (List<?>) typeListMap.getOrEmpty(dataClass);
        return list;
    }

    // rules: exact match

    @NotNull
    @Override
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> getRules(T target) {
        return exactMatch.getRules(target);
    }

    @Override
    public <T extends IKeyData<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule) {
        exactMatch.addRule(target, rule);
    }

    // rules: key match

    @Override
    @NotNull
    public <T extends IKeyData<TK, ?>, TK> List<IMakeRule<T>> getKeyRules(@NotNull TK key) {
        return keyMatch.getRules(key);
    }

    @Override
    public <T extends IKeyData<TK, ?>, TK> void addKeyRule(@NotNull TK key, @NotNull IMakeRule<T> rule) {
        keyMatch.addRule(key, rule);
    }

    // rules: key type

    @Override
    @NotNull
    public <T extends IKeyData<TK, ?>, TK> List<IMakeRule<T>> getKeyTypeRules(@NotNull Class<TK> keyType) {
        return keyTypeMatch.getRules(keyType);
    }

    @Override
    public <T extends IKeyData<TK, ?>, TK> void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule) {
        keyTypeMatch.addRule(keyType, rule);
    }

    // rules: data type

    @Override
    @NotNull
    public <T extends IKeyData<?, TT>, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType) {
        return dataTypeMatch.getRules(dataType);
    }

    @Override
    public <T extends IKeyData<?, TT>, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule) {
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

    public <T extends IKeyData<TK, TT>, TK, TT> void makePlan(T target) {

    }

    public IMakeNode makeGraph(@NotNull IKeyData<?, ?> target) {
        return null;
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void make(T target)
            throws MakeException {

        if (target.exists())
            return;

//        TK targetKey = target.getKey();
        Class<? extends TK> targetKeyType = target.getKeyType();

        for (IKeyPattern<?, ?> pattern : keyPatternRules.keySet()) {
            Class<?> patternKeyType = pattern.getKeyType();
            if (patternKeyType.isAssignableFrom(targetKeyType)) {
//                Object param = parameter.resolve(targetKey);
                for (IKeyPatternMakeRule<IKeyPattern<Object, Object>, Object, Object, IKeyData<Object, Object>, Object> patternRule : getPatternRules(pattern)) {
                    @SuppressWarnings("unchecked")
                    MakeAction<T> instance = (MakeAction<T>) patternRule.compile((IKeyData<Object, Object>) target, this);
                    if (instance == null)
                        continue;
                    IMakeRule<T> rule = instance.getRule();
                    rule.make(target, rule.getInputs());
                    return;
                }
            }
        }

        for (IMakeRule<T> rule : getRules(target)) {
            if (canMake(target)) {
                for (IKeyData<?, ?> input : rule.getInputs()) {
                    make(input);
                }
                rule.make(target, rule.getInputs());
                return;
            }
        }
        throw new MakeException("can't find a make rule for " + target);
    }

    //

    @Override
    public <T extends IKeyData<?, ?>> boolean canMake(@NotNull T target) {
        return true;
    }

}
