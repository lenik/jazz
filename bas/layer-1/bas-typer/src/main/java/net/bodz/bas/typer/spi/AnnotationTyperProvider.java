package net.bodz.bas.typer.spi;

import net.bodz.bas.meta.lang.TyperClass;
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
        TyperClass _typerClass = objType.getAnnotation(TyperClass.class);
        if (_typerClass == null)
            return null;

        Class<? extends IQueryable> annotatedClass = _typerClass.value();
        assert annotatedClass != null;

        try {
            IQueryable queryable = annotatedClass.newInstance();
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
