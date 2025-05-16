package net.bodz.bas.typer.spi.extra;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.spi.ITyperProvider;

public class ExtraTyperProvider
        implements ITyperProvider {

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

}
