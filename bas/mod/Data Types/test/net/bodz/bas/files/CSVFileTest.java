package net.bodz.bas.files;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;

import net.bodz.bas.io.Files;

import org.junit.Test;

public class CSVFileTest {

    @Test
    public void test1() throws IOException {
        CSVFile csv = new CSVFile(Files.classData(getClass(), "1")); //$NON-NLS-1$
        Iterator<String[]> it = csv.iterator();
        String[] head = it.next();
        assertEquals(3, head.length);
        assertEquals("name", head[0]); //$NON-NLS-1$
        assertEquals("age", head[1]); //$NON-NLS-1$
        assertEquals("girl", head[2]); //$NON-NLS-1$

        String[] row1 = it.next();
        assertEquals(2, row1.length);
        assertEquals("Tom", row1[0]); //$NON-NLS-1$
        assertEquals("10", row1[1]); //$NON-NLS-1$

        String[] row2 = it.next();
        assertEquals(3, row2.length);
        assertEquals("Jerry", row2[0]); //$NON-NLS-1$
        assertEquals("20", row2[1]); //$NON-NLS-1$
        assertEquals("1", row2[2]); //$NON-NLS-1$

        String[] row3 = it.next();
        assertEquals(1, row3.length);
        assertEquals("Robot", row3[0]); //$NON-NLS-1$
    }

}
