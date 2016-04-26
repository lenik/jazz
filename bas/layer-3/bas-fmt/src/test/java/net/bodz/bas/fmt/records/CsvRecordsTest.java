package net.bodz.bas.fmt.records;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class CsvRecordsTest
        extends Assert {

    @Test
    public void test1()
            throws IOException {
        URLResource classData = ClassResource.getData(getClass(), "1.csv");
        CsvRecords csv = new CsvRecords(classData, ':');
        csv.setTrim(true);

        Mitorx<CsvRow, IOException> it = csv.iterator(true);
        List<String> head = it._next();
        assertEquals(3, head.size());
        assertEquals("name", head.get(0));
        assertEquals("age", head.get(1));
        assertEquals("girl", head.get(2));

        List<String> row1 = it._next();
        assertEquals(2, row1.size());
        assertEquals("Tom", row1.get(0));
        assertEquals("10", row1.get(1));

        List<String> row2 = it._next();
        assertEquals(3, row2.size());
        assertEquals("Jerry", row2.get(0));
        assertEquals("20", row2.get(1));
        assertEquals("1", row2.get(2));

        List<String> row3 = it._next();
        assertEquals(1, row3.size());
        assertEquals("Robot", row3.get(0));
    }

}
