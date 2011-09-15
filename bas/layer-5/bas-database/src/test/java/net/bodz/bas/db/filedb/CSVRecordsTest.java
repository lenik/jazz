package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.util.loader.ClassResource;

import org.junit.Assert;
import org.junit.Test;

public class CSVRecordsTest
        extends Assert {

    @Test
    public void test1()
            throws IOException {
        URLResource classData = ClassResource.classData(getClass(), "1");
        CSVRecords csv = new CSVRecords(classData);
        Mitorx<? extends String[], IOException> it = csv.iterator(true);
        String[] head = it._next();
        assertEquals(3, head.length);
        assertEquals("name", head[0]);
        assertEquals("age", head[1]);
        assertEquals("girl", head[2]);

        String[] row1 = it._next();
        assertEquals(2, row1.length);
        assertEquals("Tom", row1[0]);
        assertEquals("10", row1[1]);

        String[] row2 = it._next();
        assertEquals(3, row2.length);
        assertEquals("Jerry", row2[0]);
        assertEquals("20", row2[1]);
        assertEquals("1", row2[2]);

        String[] row3 = it._next();
        assertEquals(1, row3.length);
        assertEquals("Robot", row3[0]);
    }

}
