package net.bodz.bas.db.impl.tfd;

import java.io.File;

import net.bodz.bas.dbi.AbstractRepository;
import net.bodz.bas.dbi.DataAccessException;

public class TfdTable<T, K>
        extends AbstractRepository<T, K> {

    File dir;
    TfdTableConfig config;

    @Override
    public T load(K key)
            throws DataAccessException {
        return null;
    }

    @Override
    public T save(K key, T value)
            throws DataAccessException {
        return null;
    }

    @Override
    public int delete(K key)
            throws DataAccessException {
        return 0;
    }

}
