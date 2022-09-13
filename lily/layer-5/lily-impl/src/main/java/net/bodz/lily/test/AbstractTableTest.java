package net.bodz.lily.test;

import org.junit.Assume;
import org.junit.Test;

import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.lily.model.base.CoObject;

public abstract class AbstractTableTest<T extends CoObject, M, mapper_t extends IGenericMapper<T, M>>
        extends AbstractTableViewTest<T, M, mapper_t> {

    public abstract T buildSample()
            throws Exception;

    @Test
    public void testInsertDelete()
            throws Exception {
        Assume.assumeTrue(getConfig().testInsert);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.id();
        assertNotNull(newId);

        if (getConfig().purge) {
            boolean result = mapper.delete(newId);
            assertTrue(result);
        }
    }

    @Test
    public void testUpdate()
            throws Exception {
        Assume.assumeTrue(getConfig().testUpdate);
        T a = buildSample();

        mapper_t mapper = getMapper();
        long num = mapper.insert(a);
        assertTrue(num > 0);
        Object newId = a.id();
        assertNotNull(newId);

        num = mapper.update(a);
        assertTrue(num > 0);

        if (getConfig().purge) {
            boolean result = mapper.delete(newId);
            assertTrue(result);
        }
    }

}
