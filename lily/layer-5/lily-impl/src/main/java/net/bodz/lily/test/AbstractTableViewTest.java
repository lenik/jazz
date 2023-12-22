package net.bodz.lily.test;

import java.lang.reflect.Type;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ibatis.IGenericMapper;

public abstract class AbstractTableViewTest<T, M, mapper_t extends IGenericMapper<T, M>>
        extends AbstractDaoTest {

    protected final Class<T> entityClass;
    protected final Class<M> maskClass;
    protected final Class<mapper_t> mapperClass;

    public AbstractTableViewTest() {
        Type[] typeArgs = TypeParam.getTypeArgs(getClass(), AbstractTableViewTest.class);
        entityClass = TypeParam.bound1(typeArgs[0]);
        maskClass = TypeParam.bound1(typeArgs[1]);
        mapperClass = TypeParam.bound1(typeArgs[2]);
    }

    public mapper_t getMapper() {
        mapper_t mapper = context.getMapper(mapperClass);
        if (mapper == null)
            throw new NullPointerException("No mapper for " + mapperClass);
        return mapper;
    }

}
