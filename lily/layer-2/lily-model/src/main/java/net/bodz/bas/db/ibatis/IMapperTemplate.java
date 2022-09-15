package net.bodz.bas.db.ibatis;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.model.base.CoObjectMask;

@ExcludedFromIndex
public interface IMapperTemplate<T extends CoObject, M extends CoObjectMask>
        extends
            IGenericMapper<T, M> {

    T select(@Param("id") Object id);

    /**
     * Insert with custom specified id value.
     *
     * @return Number of records.
     */
    long insertWithId(T obj);

    long update(T obj);

    boolean delete(@Param("id") Object id);

    T selectByCodeName(@Param("code") String codeName);

}
