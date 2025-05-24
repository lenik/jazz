package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.TypeListPmap;

public class KeyTypeMatch
        implements IMakeStrategy {

    TypeListPmap<IMakeRule<?>> rulesMap = new TypeListPmap<>();


    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IMakeSession session) {
        return (List<IMakeRule<T>>) (List<?>) getRules(target.getKeyType());
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<? extends TK, ?>, TK> List<IMakeRule<T>> getRules(@NotNull Class<TK> keyType) {
        List<IMakeRule<T>> rules = (List<IMakeRule<T>>) (List<?>) rulesMap.joinConcatenated(keyType);
        return rules;
    }

    public <T extends IKeyData<TK, ?>, TK> void addRule(@NotNull Class<TK> keyType, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(keyType, rule);
    }

}
