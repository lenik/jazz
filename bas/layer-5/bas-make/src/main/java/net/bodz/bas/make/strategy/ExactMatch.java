package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class ExactMatch
        implements IMakeStrategy {

    ListMap<IKeyData<?, ?>, IMakeRule<?>> rulesMap = new ListMap<>();

    @NotNull
    @Override
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IMakeSession session) {
        return getRules(target);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> getRules(@NotNull T target) {
        return (List<IMakeRule<T>>) (List<?>) rulesMap.getOrEmpty(target);
    }

    public <T extends IKeyData<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(target, rule);
    }

}
