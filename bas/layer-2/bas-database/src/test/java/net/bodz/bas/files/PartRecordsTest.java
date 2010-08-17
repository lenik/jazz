package net.bodz.bas.files;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.db.filedb.PartRecords;
import net.bodz.bas.db.filedb.PartRecords.PartMap;
import net.bodz.bas.io.resource.IStreamInputSource;

import org.junit.Test;

public class PartRecordsTest
        extends TestCase {

    static String map2str(Map<String, String> map) {
        if (map == null)
            return "(null)";
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        StringBuffer buf = null;
        for (String k : keys) {
            if (buf == null)
                buf = new StringBuffer();
            else
                buf.append(", ");
            String v = map.get(k);
            buf.append(k + "=" + v);
        }
        if (buf == null)
            return "";
        return buf.toString().replace("\r", "");
    }

    @Test
    public void test1()
            throws IOException {
        URL url = FileRes.classData(getClass(), "1");
        IStreamInputSource source = new URLResLink(url);
        PartRecords maps = new PartRecords(source);
        ImmediateIteratorX<? extends PartMap, ? extends IOException> it = maps.iterator(false);
        PartMap part;

        part = it.next();
        assertEquals("part A", ".=hello\nworld\n, age=10, name=a", map2str(part));

        part = it.next();
        assertEquals("part B", ".=BBB\n, age=20, location=home\n, name=b", map2str(part));

        part = it.next();
        assertEquals("part C", ".=CCC\n", map2str(part));

        part = it.next();
        assertEquals("part D", ".=DDD\n, name=d", map2str(part));

        part = it.next();
        assertEquals("part E", ".=EEE\nFFF\n", map2str(part));

        assertTrue(it.isEnded());

        assertNull(it.next());
    }

}
