package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.type.Extends;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class KeyExtendsMatch
        implements IMakeStrategy {

    ListMap<Extends, IMakeRule<?, ?, ?>> rulesMap = new ListMap<>();

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<? extends TK> keyType = target.getKeyType();
        Class<? extends TT> dataType = target.getDataType();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyType)) {
                for (IMakeRule<?, ?, ?> _rule : rulesMap.get(interfaces)) {
                    if (!keyType.isAssignableFrom(_rule.getKeyType()))
                        continue;
                    if (!dataType.isAssignableFrom(_rule.getDataType()))
                        continue;
                    @SuppressWarnings("unchecked")
                    IMakeRule<T, TK, TT> rule = (IMakeRule<T, TK, TT>) _rule;
                    return rule;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding) {
        return (List<IMakeRule<T, TK, TT>>) (List<?>) getRules(target.getKeyType());
    }

    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TT> keyType) {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyType)) {
                for (IMakeRule<?, ?, ?> _rule : rulesMap.get(interfaces)) {
                    if (!keyType.isAssignableFrom(_rule.getKeyType()))
                        continue;
                    @SuppressWarnings("unchecked")
                    IMakeRule<T, TK, TT> rule = (IMakeRule<T, TK, TT>) _rule;
                    rules.add(rule);
                }
            }
        }
        return rules;
    }

    @SafeVarargs
    @NotNull
    public final <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(Class<? super TK>... keyInterfaces) {
        return getRules(Extends.of(keyInterfaces));
    }

    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(Extends keyInterfaces) {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();
        for (Extends interfaces : rulesMap.keySet()) {
            if (interfaces.isAssignableFrom(keyInterfaces)) {
                for (IMakeRule<?, ?, ?> _rule : rulesMap.get(interfaces)) {
                    if (!keyInterfaces.isAssignableFrom(_rule.getKeyType()))
                        continue;
                    @SuppressWarnings("unchecked")
                    IMakeRule<T, TK, TT> rule = (IMakeRule<T, TK, TT>) _rule;
                    rules.add(rule);
                }
            }
        }
        return rules;
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<?>[] interfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        addRule(Extends.of(interfaces), rule);
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Extends interfaces, @NotNull IMakeRule<T, TK, TT> rule) {
        rulesMap.addToList(interfaces, rule);
    }

}
