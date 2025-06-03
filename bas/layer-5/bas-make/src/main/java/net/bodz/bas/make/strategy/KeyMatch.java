package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class KeyMatch
        implements IMakeStrategy {

    ListMap<Object, IMakeRule<?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T, TK, TT> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        return (IMakeRule<T, TK, TT>) rulesMap.getFirstOfList(target.getKey());
    }

    @SuppressWarnings("unchecked")
    @Override
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> makeRules(@NotNull T target, @NotNull IDataBinding binding) {
        Object key = target.getKey();
        return (List<IMakeRule<T, TK, TT>>) (List<?>) getRules(key);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T, TK, TT>> getRules(@NotNull TK key) {
        List<IMakeRule<T, TK, TT>> rules = (List<IMakeRule<T, TK, TT>>) (List<?>) rulesMap.getOrEmpty(key);
        return rules;
    }

    public <T extends IKeyData<TK, TT>, TK, TT> void addRule(@NotNull TK key, @NotNull IMakeRule<T, TK, TT> rule) {
        rulesMap.addToList(key, rule);
    }

}
