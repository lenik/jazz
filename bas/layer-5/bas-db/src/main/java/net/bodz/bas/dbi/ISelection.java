package net.bodz.bas.dbi;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.dbi.expr.IBoolExpr;
import net.bodz.bas.dbi.expr.IExpression;

public interface ISelection<T, K> {

    IRepository<T, K> getRepository();

    /**
     * Returns the number of elements in the selection.
     *
     * @return {@link Integer#MAX_VALUE} if the number is greater than {@link Integer#MAX_VALUE}.
     */
    long size()
            throws RuntimeDataAccessException;

    boolean isEmpty()
            throws RuntimeDataAccessException;

    Set<K> keySet()
            throws RuntimeDataAccessException;

    Collection<T> values()
            throws RuntimeDataAccessException;

    void refresh()
            throws RuntimeDataAccessException;

    void update(String field, IExpression expr)
            throws DataAccessException;

    void update(Map<String, IExpression> assignments)
            throws DataAccessException;

    void replace(Map<K, T> newValues)
            throws DataAccessException;

    int delete()
            throws DataAccessException;

    /**
     * @param offset
     *            Index (0-based) of the first result.
     * @param limit
     *            Max count of the results.
     */
    ISelection<T, K> limit(int offset, int limit);

    ISelection<T, K> filter(IBoolExpr expr);

    // ISelection<T, K> project();

}

class Test {

    IRepository<Object, Long> repo;
    {
        repo.all().limit(100, 20).filter(null);
    }

}