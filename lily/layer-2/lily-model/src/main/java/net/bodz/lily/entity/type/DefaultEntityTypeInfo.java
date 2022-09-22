package net.bodz.lily.entity.type;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.model.base.CoObjectMask;

public class DefaultEntityTypeInfo
        implements
            IEntityTypeInfo {

    Class<?> entityClass;
    Class<?> idClass;
    Class<?> mapperClass;
    Class<?> criteriaClass;

    public DefaultEntityTypeInfo(Class<?> entityClass) {
        this.entityClass = entityClass;
        idClass = IdFn._getIdType(entityClass);
        mapperClass = IMapper.fn.getMapperClass(entityClass);
        criteriaClass = CoObjectMask.findMaskClass(entityClass);
    }

    @Override
    public Class<?> getEntityClass() {
        return entityClass;
    }

    @Override
    public Class<?> getIdClass() {
        return idClass;
    }

    @Override
    public Class<?> getMapperClass() {
        return mapperClass;
    }

    @Override
    public Class<?> getCrtieriaClass() {
        return criteriaClass;
    }

}
