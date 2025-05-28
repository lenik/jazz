package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.TypeListPmap;

public class DataTypeMatch
        implements IMakeStrategy {

    TypeListPmap<IMakeRule<?>> rulesMap = new TypeListPmap<>();
    ListMap<Class<?>, IMakeRule<?>> ifaceRulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<?> dataType = target.getDataType();
        for (List<IMakeRule<?>> rules : rulesMap.join(dataType)) {
            if (!rules.isEmpty())
                return (IMakeRule<T>) rules.get(0);
        }
        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (dataType.isAssignableFrom(iface)) {
                if (!ifaceRulesMap.isListEmpty(iface))
                    return (IMakeRule<T>) ifaceRulesMap.get(iface).get(0);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding) {
        return (List<IMakeRule<T>>) (List<?>) getRules(target.getDataType());
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<?, ? extends TT>, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType) {
        List<IMakeRule<T>> rules = (List<IMakeRule<T>>) (List<?>) rulesMap.joinConcatenated(dataType);
        for (Class<?> iface : ifaceRulesMap.keySet()) {
            if (dataType.isAssignableFrom(iface)) {
                List<IMakeRule<T>> sub = (List<IMakeRule<T>>) (List<?>) ifaceRulesMap.get(iface);
                rules.addAll(sub);
            }
        }
        return rules;
    }

    public <T extends IKeyData<?, TT>, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(dataType, rule);
    }

}
