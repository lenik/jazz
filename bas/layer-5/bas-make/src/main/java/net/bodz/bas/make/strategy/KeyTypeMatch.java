package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.TypeListPmap;

public class KeyTypeMatch
        implements IMakeStrategy {

    TypeListPmap<IMakeRule<?>> rulesMap = new TypeListPmap<>();
    ListMap<Class<?>, IMakeRule<?>> ifaceRulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<?> keyType = target.getKeyType();
        for (List<IMakeRule<?>> rules : rulesMap.join(keyType)) {
            if (!rules.isEmpty())
                return (IMakeRule<T>) rules.get(0);
        }
        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (keyType.isAssignableFrom(iface)) {
                return (IMakeRule<T>) ifaceRulesMap.getFirstOfList(iface);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding) {
        return (List<IMakeRule<T>>) (List<?>) getRules(target.getKeyType());
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<? extends TK, TT>, TK, TT> List<IMakeRule<T>> getRules(@NotNull Class<TK> keyType) {
        List<IMakeRule<T>> rules = (List<IMakeRule<T>>) (List<?>) rulesMap.joinConcatenated(keyType);

        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (keyType.isAssignableFrom(iface)) {
                List<IMakeRule<T>> sub = (List<IMakeRule<T>>) (List<?>) ifaceRulesMap.get(iface);
                rules.addAll(sub);
            }
        }
        return rules;
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(keyType, rule);
    }

}
