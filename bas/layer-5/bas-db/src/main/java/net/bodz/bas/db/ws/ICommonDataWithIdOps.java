package net.bodz.bas.db.ws;

import org.apache.ibatis.annotations.Param;

public interface ICommonDataWithIdOps<T> {

    T select(@Param("id") Object id);

    /**
     * Insert with custom specified id value.
     *
     * @return number of records.
     */
    @Deprecated
    default long insertWithId(T obj) {
        return insertExplicit(obj);
    }

    long insertExplicit(T obj);

    long update(T obj);

    boolean delete(@Param("id") Object id);

}
