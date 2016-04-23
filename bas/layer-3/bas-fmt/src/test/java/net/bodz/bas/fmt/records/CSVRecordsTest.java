package net.bodz.bas.fmt.records;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class CSVRecordsTest
        extends Assert {

    @Test
    public void test1()
            throws IOException {
        URLResource classData = ClassResource.getData(getClass(), "1.csv");
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
