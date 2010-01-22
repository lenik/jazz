package net.bodz.bas.files;

import static net.bodz.bas.files.PartRecordsTest.map2str;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.db.filedb.INIRecords;

import org.junit.Test;

public class INIRecordsTest {

    ResLink resLink;

    public INIRecordsTest() {
        URL classData = FileRes.classData(getClass(), "1");
        resLink = new URLResLink(classData);
    }

    @Test
    public void testByGroup()
            throws IOException {
        INIRecords records = new INIRecords(resLink, false);
        ImmediateIteratorX<? extends Map<String, String>, ? extends IOException> iter = records.iterator(true);

        Map<String, String> p1 = iter.next();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1));

        Map<String, String> p2 = iter.next();
        assertEquals("_section=you, age=[what], name=hi", map2str(p2));

        Map<String, String> p3 = iter.next();
        assertEquals("_section=him, location=home", map2str(p3));

        assertNull(iter.next());
    }

    @Test
    public void testFlatten()
            throws IOException {
        INIRecords records = new INIRecords(resLink, true);
        Map<String, String> all = records.iterator(true).next();
        assertEquals("a=1, b=2, c=hello, world!" + ", him.exist=true, him.location=home" // 
                + ", you.age=[what], you.exist=true, you.name=hi", // 
                map2str(all));
    }

}
