package net.bodz.bas.make.strategy;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DataExtendsMatch
        implements IMakeStrategy {

    ListMap<List<Class<?>>, IMakeRule<?, ?, ?>> rulesMap = new ListMap<>();

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Class<? extends TT> dataType = target.getDataType();
        for (List<Class<?>> superTypes : rulesMap.keySet()) {
            if (isAssignableFrom(superTypes, dataType)) {
                for (IMakeRule<?, ?, ?> _rule : rulesMap.get(superTypes)) {
                    if (dataType.isAssignableFrom(_rule.getDataType())) {
                        @SuppressWarnings("unchecked")
                        IMakeRule<T, TK, TT> rule = (IMakeRule<T, TK, TT>) _rule;
                        return rule;
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
        return (List<IMakeRule<T, TK, TT>>) (List<?>) getRules(target.getDataType());
    }

    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull Class<TT> dataType) {
        List<IMakeRule<T, TK, TT>> rules = new ArrayList<>();
        for (List<Class<?>> superTypes : rulesMap.keySet()) {
            if (isAssignableFrom(superTypes, dataType)) {
                for (IMakeRule<?, ?, ?> _rule : rulesMap.get(superTypes)) {
                    if (dataType.isAssignableFrom(_rule.getDataType())) {
                        @SuppressWarnings("unchecked")
                        IMakeRule<T, TK, TT> rule = (IMakeRule<T, TK, TT>) _rule;
                        rules.add(rule);
                    }
                }
            }
        }
        return rules;
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull Class<?>[] superTypes, @NotNull IMakeRule<T, TK, TT> rule) {
        addRule(Arrays.asList(superTypes), rule);
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull List<Class<?>> superTypes, @NotNull IMakeRule<T, TK, TT> rule) {
        rulesMap.addToList(superTypes, rule);
    }

    static boolean isAssignableFrom(Iterable<Class<?>> superTypes, Class<?> type) {
        for (Class<?> superType : superTypes)
            if (!superType.isAssignableFrom(type))
                return false;
        return true;
    }

}
