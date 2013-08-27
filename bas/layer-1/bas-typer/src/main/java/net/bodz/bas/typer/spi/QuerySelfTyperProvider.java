package net.bodz.bas.typer.spi;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

/**
 * This provider simply cast this object to specific typer class.
 */
public class QuerySelfTyperProvider
        extends AbstractTyperProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.immediateQuery.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        if (obj instanceof IQueryable) {
            T typers = ((IQueryable) obj).query(typerClass);
            return typers;
        }
        return null;
    }
}
