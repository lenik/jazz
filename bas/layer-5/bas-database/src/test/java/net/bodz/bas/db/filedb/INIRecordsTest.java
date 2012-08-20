package net.bodz.bas.db.filedb;

import static net.bodz.bas.db.filedb.MultiTextMapsTest.map2str;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.util.iter.Mitorx;

public class INIRecordsTest
        extends Assert {

    IStreamInputSource source;

    public INIRecordsTest() {
        source = ClassResource.classData(getClass(), "1");
    }

    @Test
    public void testByGroup()
            throws IOException {
        INIRecords records = new INIRecords(source, false);
        Mitorx<? extends Map<String, String>, ? extends IOException> iter = records.iterator(true);

        Map<String, String> p1 = iter._next();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1));

        Map<String, String> p2 = iter._next();
        assertEquals("_section=you, age=[what], name=hi", map2str(p2));

        Map<String, String> p3 = iter._next();
        assertEquals("_section=him, location=home", map2str(p3));

        assertNull(iter._next());
    }

    @Ignore
    @Test
    public void testFlatten()
            throws IOException {
        INIRecords records = new INIRecords(source, true);
        Map<String, String> all = records.iterator(true)._next();
        assertEquals("a=1, b=2, c=hello, world!" + ", him.exist=true, him.location=home" //
                + ", you.age=[what], you.exist=true, you.name=hi", //
                map2str(all));
    }

}
