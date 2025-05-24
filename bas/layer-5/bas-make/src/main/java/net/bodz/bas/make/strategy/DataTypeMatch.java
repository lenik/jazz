package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.TypeListPmap;

public class DataTypeMatch
        implements IMakeStrategy {

    TypeListPmap<IMakeRule<?>> rulesMap = new TypeListPmap<>();

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyData<?, ?>> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IMakeSession session) {
        return (List<IMakeRule<T>>) (List<?>) getRules(target.getDataType());
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T extends IKeyData<?, ? extends TT>, TT> List<IMakeRule<T>> getRules(@NotNull Class<TT> dataType) {
        List<IMakeRule<T>> rules = (List<IMakeRule<T>>) (List<?>) rulesMap.joinConcatenated(dataType);
        return rules;
    }

    public <T extends IKeyData<?, TT>, TT> void addRule(@NotNull Class<TT> dataType, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(dataType, rule);
    }

}
