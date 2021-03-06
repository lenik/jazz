package net.bodz.lily.test;

import java.lang.reflect.Type;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.test.DaoTestConfig;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.util.mapper.TableProfiles;

public abstract class AbstractMapperTest<T extends CoObject, M, mapper_t extends IMapperTemplate<T, M>>
        extends Assert {

    protected DataContext context = getContext();
    protected TableProfiles tables = new TableProfiles(context);

    protected final Class<T> entityClass;
    protected final Class<M> maskClass;
    protected final Class<mapper_t> mapperClass;

    protected final Random random = new Random();

    public AbstractMapperTest() {
        Type[] typeArgs = TypeParam.getTypeArgs(getClass(), AbstractMapperTest.class);
        entityClass = TypeParam.bound1(typeArgs[0]);
        maskClass = TypeParam.bound1(typeArgs[1]);
        mapperClass = TypeParam.bound1(typeArgs[2]);
    }

    protected abstract DataContext getContext();

    public abstract T buildSample();

    public DaoTestConfig getConfig() {
        return DaoTestConfig.global;
    }

    public mapper_t getMapper() {
        mapper_t mapper = context.getMapper(mapperClass);
        if (mapper == null)
            throw new NullPointerException("No mapper for " + mapperClass);
        return mapper;
    }

    @Test
    public void testInsertDelete() {
        Assume.assumeTrue(getConfig().testInsert);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.getId();
        assertNotNull(newId);

        if (getConfig().purge) {
            boolean result = mapper.delete(newId);
            assertTrue(result);
        }
    }

    @Test
    public void testUpdate() {
        Assume.assumeTrue(getConfig().testUpdate);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.getId();
        assertNotNull(newId);

        num = mapper.update(a);
        assertTrue(num > 0);

        if (getConfig().purge) {
            boolean result = mapper.delete(newId);
            assertTrue(result);
        }
    }

}
