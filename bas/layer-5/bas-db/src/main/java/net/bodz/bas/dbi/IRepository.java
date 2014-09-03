package net.bodz.bas.dbi;

public interface IRepository<T, K> {

    T load(K key)
            throws DataAccessException;

    T save(K key, T value)
            throws DataAccessException;

    int delete(K key)
            throws DataAccessException;

    ISelection<T, K> all();

}
