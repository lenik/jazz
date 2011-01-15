package net.bodz.bas.db.filedb;

import static net.bodz.bas.db.filedb.PartRecordsTest.map2str;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.util.ClassResource;

import org.junit.Test;

public class INIRecordsTest
        extends TestCase {

    IStreamInputSource source;

    public INIRecordsTest() {
        source = ClassResource.classData(getClass(), "1");
    }

    @Test
    public void testByGroup()
            throws IOException {
        INIRecords records = new INIRecords(source, false);
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
        INIRecords records = new INIRecords(source, true);
        Map<String, String> all = records.iterator(true).next();
        assertEquals("a=1, b=2, c=hello, world!" + ", him.exist=true, him.location=home" //
                + ", you.age=[what], you.exist=true, you.name=hi", //
                map2str(all));
    }

}
