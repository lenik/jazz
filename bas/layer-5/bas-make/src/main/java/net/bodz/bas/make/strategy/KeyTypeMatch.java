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

    TypeListPmap<IMakeRule<?, ?, ?>> rulesMap = new TypeListPmap<>();
    ListMap<Class<?>, IMakeRule<?, ?, ?>> ifaceRulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<?> keyType = target.getKeyType();
        for (List<IMakeRule<?, ?, ?>> rules : rulesMap.join(keyType)) {
            if (!rules.isEmpty())
                return (IMakeRule<T, TK, TT>) rules.get(0);
        }
        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (keyType.isAssignableFrom(iface)) {
                return (IMakeRule<T, TK, TT>) ifaceRulesMap.getFirstOfList(iface);
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

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TK> keyType) {
        List<IMakeRule<T, TK, TT>> rules = (List<IMakeRule<T, TK, TT>>) (List<?>) rulesMap.joinConcatenated(keyType);

        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (keyType.isAssignableFrom(iface)) {
                List<IMakeRule<T, TK, TT>> sub = (List<IMakeRule<T, TK, TT>>) (List<?>) ifaceRulesMap.get(iface);
                rules.addAll(sub);
            }
        }
        return rules;
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T, TK, TT> rule) {
        rulesMap.addToList(keyType, rule);
    }

}
