package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.QueryException;
import net.bodz.bas.traits.IInstanceStore;
import net.bodz.bas.traits.Traits;
import net.bodz.bas.traits.provider.AbstractTraitsProvider;

public class CompanionTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.PRIORITY_COMPANION;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        String objTypeName = objType.getName();

        String companionTraitsTypeName = objTypeName + "Traits";
        Class<?> companionTraitsType;
        try {
            companionTraitsType = Class.forName(companionTraitsTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }

        if (!traitsType.isAssignableFrom(companionTraitsType))
            return null;

        @SuppressWarnings("unchecked")
        IInstanceStore<? extends T> companionTraitsInstanceStore = Traits.getTraits(companionTraitsType,
                IInstanceStore.class);
        T traits = companionTraitsInstanceStore.getInstance(null);
        if (traits == null)
            throw new NullPointerException("traits");
        return traits;
    }

}
