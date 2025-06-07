package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.pattern.key.IKeyExtendsPattern;
import net.bodz.bas.make.pattern.key.IKeyExtendsPatternMakeRule;
import net.bodz.bas.make.type.Extends;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.MapMap;

public class KeyExtendsPatternMatch
        implements IMakeStrategy {

    MapMap<Extends, IKeyExtendsPattern<?, ?>, IKeyExtendsPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new MapMap<>();

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<? extends TK> keyType = target.getKeyType();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyType)) {
                Map<IKeyExtendsPattern<?, ?>, IKeyExtendsPatternMakeRule<?, ?, ?, ?, ?>> patternMap = rulesMap.get(interfaces);
                for (IKeyExtendsPattern<?, ?> pattern : patternMap.keySet()) {
                    @SuppressWarnings("rawtypes")
                    IKeyExtendsPatternMakeRule patternRule = patternMap.get(pattern);
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
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding) {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();
        Class<? extends TK> keyType = target.getKeyType();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyType)) {
                Map<IKeyExtendsPattern<?, ?>, IKeyExtendsPatternMakeRule<?, ?, ?, ?, ?>> patternMap = rulesMap.get(interfaces);
                for (IKeyExtendsPattern<?, ?> pattern : patternMap.keySet()) {
                    @SuppressWarnings("rawtypes")
                    IKeyExtendsPatternMakeRule patternRule = patternMap.get(pattern);
                    try {
                        @SuppressWarnings("unchecked")
                        BoundRule<T, TK, TT> action = patternRule.compile(target, binding);
                        if (action != null)
                            rules.add(action.getRule());
                    } catch (CompileException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return rules;
    }

    @SafeVarargs
    @NotNull
    public final <Tp extends IKeyExtendsPattern<Param, TK>, Param, T extends IKeyData<TK, TT>, TK, TT> //
    List<IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT>> getRules(Class<? super TK>... keyInterfaces) {
        return getRules(Extends.of(keyInterfaces));
    }

    @NotNull
    public <Tp extends IKeyExtendsPattern<Param, TK>, Param, T extends IKeyData<TK, TT>, TK, TT> //
    List<IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT>> getRules(Extends keyInterfaces) {
        List<IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT>> rules = new ArrayList<>();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyInterfaces)) {
                Map<IKeyExtendsPattern<?, ?>, IKeyExtendsPatternMakeRule<?, ?, ?, ?, ?>> patternMap = rulesMap.get(interfaces);
                for (IKeyExtendsPattern<?, ?> pattern : patternMap.keySet()) {
                    @SuppressWarnings("unchecked")
                    IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT> patternRule //
                            = (IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT>) (Object) patternMap.get(pattern);
                    rules.add(patternRule);
                }
            }
        }
        return rules;
    }

    public <Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addRule(Class<? super K>[] keyInterfaces, @NotNull IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> rule) {
        addRule(Extends.of(keyInterfaces), rule);
    }

    public <Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addRule(Extends keyInterfaces, @NotNull IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> rule) {
        rulesMap.putToMap(keyInterfaces, rule.getPattern(), rule);
    }

}
