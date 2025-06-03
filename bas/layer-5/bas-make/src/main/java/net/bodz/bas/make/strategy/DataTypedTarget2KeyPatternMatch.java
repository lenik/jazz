package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTarget2KeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedTarget2KeyPatternMakeRule;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKey;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DataTypedTarget2KeyPatternMatch
        implements IMakeStrategy {

    ListMap<IDataTypedTarget2KeyPattern<?, ?, ?, ?>, IDataTypedTarget2KeyPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("rawtypes")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IDataTypedTarget2KeyPattern<?, ?, ?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = pattern.getKeyType();
            if (!patternTargetType.isInstance(target))
                continue;

            Class<?> patternDataType = pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            for (IDataTypedTarget2KeyPatternMakeRule patternRule : getRules(pattern)) {
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

        for (IDataTypedTarget2KeyPattern<?, ?, ?, ?> _pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = _pattern.getKeyType();
            if (!patternTargetType.isInstance(target))
                continue;

            Class<?> patternDataType = _pattern.getDataType();
            if (!patternDataType.isAssignableFrom(target.getDataType()))
                continue;

            @SuppressWarnings("unchecked")
            IDataTypedTarget2KeyPattern<?, T, TK, TT> pattern = (IDataTypedTarget2KeyPattern<?, T, TK, TT>) _pattern;

L:
            for (IDataTypedTarget2KeyPatternMakeRule<?, ?, ?, ?, ?> _patternRule : getRules(pattern)) {

                @SuppressWarnings("unchecked")
                IDataTypedTarget2KeyPatternMakeRule<?, ?, TK, T, TT> patternRule =//
                        (IDataTypedTarget2KeyPatternMakeRule<?, ?, TK, T, TT>) _patternRule;

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
    public <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> getRules(IDataTypedTarget2KeyPattern<?, ?, ?, ?> pattern) {
        return (List<IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
