package net.bodz.lily.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import org.junit.Assume;
import org.junit.Test;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.dao.IEntityManager;
import net.bodz.lily.entity.IId;

public abstract class AbstractManagerTest<T extends IId<?>, //
        mapper_t extends IEntityMapper<T>, //
        man_t extends IEntityManager<T>>
        extends AbstractDaoTest {

    protected final Class<T> entityClass;
    protected final Class<mapper_t> mapperClass;
    protected final Class<man_t> managerClass;

    mapper_t mapper;
    man_t manager;

    public AbstractManagerTest() {
        Type[] typeArgs = TypeParam.getTypeArgs(getClass(), AbstractTableViewTest.class);
        entityClass = TypeParam.bound1(typeArgs[0]);
        mapperClass = TypeParam.bound1(typeArgs[2]);
        managerClass = TypeParam.bound1(typeArgs[3]);

        mapper = context.getMapper(mapperClass);

        try {
            Constructor<man_t> ctor = managerClass.getConstructor(DataContext.class);
            manager = ctor.newInstance(context);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public synchronized man_t getManager() {
        return manager;
    }

    public T buildSample()
            throws Exception {
        return null;
    }

    @Test
    public void testInsertDelete()
            throws Exception {
        Assume.assumeTrue(getConfig().testInsert);
        T a = buildSample();

        man_t manager = getManager();
        long num = manager.insert(a);
        assertTrue(num > 0);
        Object newId = a.id();
        assertNotNull(newId);

        if (getConfig().purge) {
            boolean result = manager.delete(newId);
            assertTrue(result);
        }
    }

    @Test
    public void testUpdate()
            throws Exception {
        Assume.assumeTrue(getConfig().testUpdate);
        T a = buildSample();

        man_t manager = getManager();
        long num = manager.insert(a);
        assertTrue(num > 0);
        Object newId = a.id();
        assertNotNull(newId);

        num = manager.update(a);
        assertTrue(num > 0);

        if (getConfig().purge) {
            boolean result = manager.delete(newId);
            assertTrue(result);
        }
    }

}
