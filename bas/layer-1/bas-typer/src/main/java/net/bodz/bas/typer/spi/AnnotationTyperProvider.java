package net.bodz.bas.typer.spi;

import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.rtx.IQueryable;

public class AnnotationTyperProvider
        implements ITyperProvider {

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
            IQueryable queryable = typerFamilyClass.getConstructor().newInstance();
            return queryable.query(typerClass);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
