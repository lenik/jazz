package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.IKeyPatternMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class KeyPatternMatch
        implements IMakeStrategy {

    ListMap<IKeyPattern<?, ?>, IKeyPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("rawtypes")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (IKeyPattern<?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternKeyType = pattern.getKeyType();
            if (!patternKeyType.isAssignableFrom(target.getKeyType()))
                continue;

            for (IKeyPatternMakeRule patternRule : getRules(pattern)) {
                try {
                    @SuppressWarnings("unchecked")
                    BoundRule<T> action = patternRule.compile(target, binding);
                    if (action != null)
                        return action.getRule();
                } catch (CompileException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T>> rules = new ArrayList<>();

        for (IKeyPattern<?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternKeyType = pattern.getKeyType();
            if (!patternKeyType.isAssignableFrom(target.getKeyType()))
                continue;

            for (IKeyPatternMakeRule patternRule : getRules(pattern)) {
                @SuppressWarnings("unchecked")
                BoundRule<T> action = (BoundRule<T>) patternRule.compile(target, binding);
                if (action == null)
                    continue;

                IMakeRule<T> rule = action.getRule();
                rules.add(rule);
            }
        }
        return rules;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IKeyPatternMakeRule<Tp, Param, K, T, TT>> getRules(IKeyPattern<?, ?> pattern) {
        return (List<IKeyPatternMakeRule<Tp, Param, K, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull IKeyPatternMakeRule<Tp, Param, K, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
