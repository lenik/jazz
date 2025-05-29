package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTargetPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTargetPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DataTypedTargetPatternMatch
        implements IMakeStrategy {

    ListMap<IDataTypedTargetPattern<?, ?, ?, ?>, IDataTypedTargetPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("rawtypes")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IDataTypedTargetPattern<?, ?, ?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = pattern.getKeyType();
            if (!patternTargetType.isInstance(target))
                continue;

            Class<?> patternDataType = pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            for (IDataTypedTargetPatternMakeRule patternRule : getRules(pattern)) {
                @SuppressWarnings("unchecked")
                BoundRule<T> action = (BoundRule<T>) patternRule.compile(target, binding);
                if (action == null)
                    continue;

                IMakeRule<T> rule = action.getRule();
                return rule;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T>> rules = new ArrayList<>();

        for (IDataTypedTargetPattern<?, ?, ?, ?> _pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = _pattern.getKeyType();
            if (!patternTargetType.isInstance(target))
                continue;

            Class<?> patternDataType = _pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            @SuppressWarnings("unchecked")
            IDataTypedTargetPattern<?, T, TK, TT> pattern = (IDataTypedTargetPattern<?, T, TK, TT>) _pattern;

L:
            for (IDataTypedTargetPatternMakeRule<?, ?, ?, ?, ?> _patternRule : getRules(pattern)) {

                @SuppressWarnings("unchecked")
                IDataTypedTargetPatternMakeRule<?, ?, TK, T, TT> patternRule =//
                        (IDataTypedTargetPatternMakeRule<?, ?, TK, T, TT>) _patternRule;

                BoundRule<T> action = patternRule.compile(target, binding);
                if (action == null)
                    continue;
                IKeyData<?, ?>[] inputs = action.getInputs();

                IDataTypedParameterizedKeys<?, ?, ?>[] patternInputs = patternRule.getInputs();
                for (int i = 0; i < patternInputs.length; i++) {
                    Class<?> patternType = patternInputs[i].getDataType();
                    Class<?> inputType = inputs[i].getDataType();
                    if (!patternType.isAssignableFrom(inputType))
                        continue L;
                }

                IMakeRule<T> rule = action.getRule();
                rules.add(rule);
            }
        }
        return rules;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT>> getRules(IDataTypedTargetPattern<?, ?, ?, ?> pattern) {
        return (List<IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
