package net.bodz.bas.typer.spi.extra;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.spi.AbstractTyperProvider;

public class ExtraTyperProvider
        extends AbstractTyperProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    /**
     * TODO Not implemented.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        if (objType.isEnum()) {
            EnumTypers<?> enumTypers = new EnumTypers(objType);
            T typer = enumTypers.query(typerClass);
            if (typer != null)
                return typer;
        }
        return null;
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        return null;
    }

}
