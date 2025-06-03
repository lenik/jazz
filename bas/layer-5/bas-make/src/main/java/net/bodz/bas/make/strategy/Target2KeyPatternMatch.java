package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.key.ITarget2KeyPattern;
import net.bodz.bas.make.pattern.key.ITarget2KeyPatternMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class Target2KeyPatternMatch
        implements IMakeStrategy {

    ListMap<ITarget2KeyPattern<?, ?, ?, ?>, ITarget2KeyPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("rawtypes")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        for (ITarget2KeyPattern<?, ?, ?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = pattern.getTargetType();
            if (!patternTargetType.isInstance(target))
                continue;

            for (ITarget2KeyPatternMakeRule patternRule : getRules(pattern)) {
                try {
                    @SuppressWarnings("unchecked")
                    BoundRule<T, TK, TT> action = patternRule.compile(target, binding);
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
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();

        for (ITarget2KeyPattern<?, ?, ?, ?> pattern : rulesMap.keySet()) {
            Class<?> patternTargetType = pattern.getKeyType();
            if (!patternTargetType.isInstance(target))
                continue;

            for (ITarget2KeyPatternMakeRule patternRule : getRules(pattern)) {
                @SuppressWarnings("unchecked")
                BoundRule<T, TK, TT> action = (BoundRule<T, TK, TT>) patternRule.compile(target, binding);
                if (action == null)
                    continue;

                IMakeRule<T, TK, TT> rule = action.getRule();
                rules.add(rule);
            }
        }
        return rules;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    List<ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> getRules(ITarget2KeyPattern<?, ?, ?, ?> pattern) {
        return (List<ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
