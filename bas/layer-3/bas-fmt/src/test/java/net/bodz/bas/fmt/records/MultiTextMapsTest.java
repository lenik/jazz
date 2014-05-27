package net.bodz.bas.fmt.records;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.fmt.records.MultiTextMaps;
import net.bodz.bas.fmt.records.MultiTextMaps.PartMap;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class MultiTextMapsTest
        extends Assert {

    static String map2str(Map<String, String> map) {
        if (map == null)
            return "(null)";
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        StringBuilder buf = null;
        for (String k : keys) {
            if (buf == null)
                buf = new StringBuilder();
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
        IStreamInputSource source = ClassResource.getData(getClass(), "1");
        MultiTextMaps maps = new MultiTextMaps(source);

        for (Map<String, String> pm : maps) {
            System.out.println("M=" + pm);
        }
        Mitorx<? extends PartMap, ? extends IOException> it = maps.iterator(false);
        PartMap part;

        part = it._next();
        assertEquals("part A", ".=hello\nworld\n, age=10, name=a", map2str(part));

        part = it._next();
        assertEquals("part B", ".=BBB\n, age=20, location=home\n, name=b", map2str(part));

        part = it._next();
        assertEquals("part C", ".=CCC\n", map2str(part));

        part = it._next();
        assertEquals("part D", ".=DDD\n, name=d", map2str(part));

        part = it._next();
        assertEquals("part E", ".=EEE\nFFF\n", map2str(part));

        assertNull(it._next());
        assertTrue(it.isEnded());
    }

}
