package net.bodz.bas.db.ctx;

public interface IDataContexts<K> {

    /**
     * @return <code>null</code> if no context available for the specified key.
     */
    DataContext get(K key);

    void remove(K key);

    void removeAll();

    void purge(K key);

    void purgeAll();

}
