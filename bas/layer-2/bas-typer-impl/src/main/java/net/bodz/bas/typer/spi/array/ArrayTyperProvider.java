package net.bodz.bas.typer.spi.array;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.spi.ITyperProvider;

public class ArrayTyperProvider
        implements ITyperProvider {

    static Map<Class<?>, Object> map = new HashMap<>();

    static {
        map.put(byte[].class, new ByteArrayTypers());
        map.put(char[].class, new CharArrayTypers());
    }

    @Override
    public <T> T getTyper(Class<?> type, Class<T> typerClass)
            throws QueryException {
        if (!type.isArray())
            return null;

        Object typers = map.get(type);
        if (typers == null)
            return null;

        if (typerClass.isInstance(typers))
            return typerClass.cast(typers);

        if (typers instanceof IQueryable) {
            IQueryable queryable = (IQueryable) typers;
            T ans = queryable.query(typerClass);
            if (ans != null)
                return ans;
        }

        return null;
    }

}
