package net.bodz.bas.typer.spi;

import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

public class AnnotationTyperProvider
        extends AbstractTyperProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.annotation.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTyper(Class<?> objType, Class<T> typerClass) {
        if (objType == null)
            throw new NullPointerException("objType");
        typer.family _typerFamily = objType.getAnnotation(typer.family.class);
        if (_typerFamily == null)
            return null;

        Class<? extends IQueryable> typerFamilyClass = _typerFamily.value();
        assert typerFamilyClass != null;

        try {
            IQueryable queryable = typerFamilyClass.newInstance();
            return queryable.query(typerClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        return getTyper(objType, typerClass);
    }

}
