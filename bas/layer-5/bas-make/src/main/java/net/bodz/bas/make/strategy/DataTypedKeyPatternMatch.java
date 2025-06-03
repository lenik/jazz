package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKey;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DataTypedKeyPatternMatch
        implements IMakeStrategy {

    ListMap<IDataTypedKeyPattern<?, ?, ?>, IDataTypedKeyPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("rawtypes")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IDataTypedKeyPattern<?, ?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternKeyType = pattern.getKeyType();
            if (!patternKeyType.isAssignableFrom(target.getKeyType()))
                continue;

            Class<?> patternDataType = pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            for (IDataTypedKeyPatternMakeRule patternRule : getRules(pattern)) {
                @SuppressWarnings("unchecked")
                BoundRule<T, TK, TT> action = (BoundRule<T, TK, TT>) patternRule.compile(target, binding);
                if (action == null)
                    continue;

                IMakeRule<T, TK, TT> rule = action.getRule();
                return rule;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();

        for (IDataTypedKeyPattern<?, ?, ?> _pattern : rulesMap.keySet()) {
            Class<?> patternKeyType = _pattern.getKeyType();
            if (!patternKeyType.isAssignableFrom(target.getKeyType()))
                continue;

            Class<?> patternDataType = _pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            @SuppressWarnings("unchecked")
            IDataTypedKeyPattern<?, TK, TT> pattern = (IDataTypedKeyPattern<?, TK, TT>) _pattern;

L:
            for (IDataTypedKeyPatternMakeRule<?, ?, ?, ?, ?> _patternRule : getRules(pattern)) {

                @SuppressWarnings("unchecked")
                IDataTypedKeyPatternMakeRule<?, ?, TK, T, TT> patternRule =//
                        (IDataTypedKeyPatternMakeRule<?, ?, TK, T, TT>) _patternRule;

                BoundRule<T, TK, TT> action = patternRule.compile(target, binding);
                if (action == null)
                    continue;
                IKeyData<?, ?>[] inputs = action.getInputs();

                IDataTypedParameterizedKey<?, ?, ?>[] patternInputs = patternRule.getInputs();
                for (int i = 0; i < patternInputs.length; i++) {
                    Class<?> patternType = patternInputs[i].getDataType();
                    Class<?> inputType = inputs[i].getDataType();
                    if (!patternType.isAssignableFrom(inputType))
                        continue L;
                }

                IMakeRule<T, TK, TT> rule = action.getRule();
                rules.add(rule);
            }
        }
        return rules;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> getRules(IDataTypedKeyPattern<?, ?, ?> pattern) {
        return (List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
