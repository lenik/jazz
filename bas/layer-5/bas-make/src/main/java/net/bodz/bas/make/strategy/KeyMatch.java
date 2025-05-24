package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class KeyMatch
        implements IMakeStrategy {

    ListMap<Object, IMakeRule<?>> rulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @Override
    @NotNull
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IMakeSession session) {
        Object key = target.getKey();
        return (List<IMakeRule<T>>) (List<?>) getRules(key);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<TK, ?>, TK> List<IMakeRule<T>> getRules(@NotNull TK key) {
        List<IMakeRule<T>> rules = (List<IMakeRule<T>>) (List<?>) rulesMap.getOrEmpty(key);
        return rules;
    }

    public <T extends IKeyData<TK, ?>, TK> void addRule(@NotNull TK key, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(key, rule);
    }

}
