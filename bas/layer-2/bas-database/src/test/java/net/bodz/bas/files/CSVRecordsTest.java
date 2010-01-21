package net.bodz.bas.files;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.db.filedb.CSVRecords;
import net.bodz.bas.fs.legacy.Files;

import org.junit.Test;

public class CSVRecordsTest {

    @Test
    public void test1()
            throws IOException {
        URL classData = Files.classData(getClass(), "1");
        CSVRecords csv = new CSVRecords(new URLResLink(classData));
        ImmediateIteratorX<? extends String[], IOException> it = csv.iterator(true);
        String[] head = it.next();
        assertEquals(3, head.length);
        assertEquals("name", head[0]);
        assertEquals("age", head[1]);
        assertEquals("girl", head[2]);

        String[] row1 = it.next();
        assertEquals(2, row1.length);
        assertEquals("Tom", row1[0]);
        assertEquals("10", row1[1]);

        String[] row2 = it.next();
        assertEquals(3, row2.length);
        assertEquals("Jerry", row2[0]);
        assertEquals("20", row2[1]);
        assertEquals("1", row2[2]);

        String[] row3 = it.next();
        assertEquals(1, row3.length);
        assertEquals("Robot", row3[0]);
    }

}
