package net.bodz.lily.model.test;

import java.lang.reflect.Type;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.test.DaoTestConfig;
import net.bodz.lily.model.base.CoObject;

public abstract class AbstractMapperTest<T extends CoObject, M, mapper_t extends IMapperTemplate<T, M>>
        extends Assert {

    protected final Class<T> entityClass;
    protected final Class<M> maskClass;
    protected final Class<mapper_t> mapperClass;

    public AbstractMapperTest() {
        Type[] typeArgs = TypeParam.getTypeArgs(getClass(), AbstractMapperTest.class);
        entityClass = TypeParam.bound1(typeArgs[0]);
        maskClass = TypeParam.bound1(typeArgs[1]);
        mapperClass = TypeParam.bound1(typeArgs[2]);
    }

    public abstract DataContext getContext();

    public abstract T buildSample();

    public DaoTestConfig getConfig() {
        return DaoTestConfig.global;
    }

    public mapper_t getMapper() {
        DataContext context = getContext();
        return context.getMapper(mapperClass);
    }

    @Test
    public void testInsertDelete() {
        Assume.assumeTrue(getConfig().testInsert);
        Assume.assumeTrue(getConfig().testDelete);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.getId();
        assertNotNull(newId);

        boolean result = mapper.delete(newId);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        Assume.assumeTrue(getConfig().testInsert);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.getId();
        assertNotNull(newId);

        num = mapper.update(a);
        assertTrue(num > 0);

        boolean result = mapper.delete(newId);
        assertTrue(result);
    }

}
