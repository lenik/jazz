package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.IQueryable;
import net.bodz.bas.meta.lang.TraitsClass;
import net.bodz.bas.traits.provider.AbstractTraitsProvider;

public class AnnotationTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.annotation.getPriority();
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType) {
        TraitsClass traitsClassAnnotation = objType.getAnnotation(TraitsClass.class);
        if (traitsClassAnnotation == null)
            return null;

        Class<? extends IQueryable> traitsClass = traitsClassAnnotation.value();
        assert traitsClass != null;

        try {
            IQueryable queryable = traitsClass.newInstance();
            return queryable.query(traitsType);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
