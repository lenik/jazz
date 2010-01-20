package net.bodz.bas.files;

import static net.bodz.bas.files.PartRecordsTest.map2str;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import net.bodz.bas.db.filedb.INIRecords;
import net.bodz.bas.fs.legacy.Files;

import org.junit.Test;

public class INIRecordsTest {

    ResLink resLink;

    public INIRecordsTest() {
        URL classData = Files.classData(getClass(), "1");
        resLink = new URLResLink(classData);
    }

    @Test
    public void testByGroup()
            throws IOException {
        INIRecords records = new INIRecords(resLink, false);
        DirectIterator<? extends Map<String, String>, IOException> iter = records.iterator();

        Map<String, String> p1 = iter.getNext();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1));

        Map<String, String> p2 = iter.getNext();
        assertEquals("_section=you, age=[what], name=hi", map2str(p2));

        Map<String, String> p3 = iter.getNext();
        assertEquals("_section=him, location=home", map2str(p3));

        assertFalse(iter.next());
    }

    @Test
    public void testFlatten()
            throws IOException {
        INIRecords records = new INIRecords(resLink, true);
        Map<String, String> all = records.iterator().getNext();
        assertEquals("a=1, b=2, c=hello, world!" + ", him.exist=true, him.location=home" // 
                + ", you.age=[what], you.exist=true, you.name=hi", // 
                map2str(all));
    }

}
