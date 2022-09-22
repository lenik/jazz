package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.entity.IId;

public interface IEntityManager<T extends IId<Id>, Id> {

    Class<? extends T> getEntityType();

    Class<? extends Id> getIdType();

    // Class<?> getMapperClass();

    <M> IEntityMapper<T, M> getMapper();

}
