package net.bodz.bas.trait.spi;

import net.bodz.bas.meta.lang.TraitClass;
import net.bodz.bas.rtx.IQueryable;

public class AnnotationTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.annotation.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTrait(Class<?> objType, Class<T> traitType) {
        if (objType == null)
            throw new NullPointerException("objType");
        TraitClass traitClassAnnotation = objType.getAnnotation(TraitClass.class);
        if (traitClassAnnotation == null)
            return null;

        Class<? extends IQueryable> traitClass = traitClassAnnotation.value();
        assert traitClass != null;

        try {
            IQueryable queryable = traitClass.newInstance();
            return queryable.query(traitType);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
