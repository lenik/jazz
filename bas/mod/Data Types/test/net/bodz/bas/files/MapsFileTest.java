package net.bodz.bas.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.files.MapsFile.PartMap;
import net.bodz.bas.io.Files;

import org.junit.Test;

public class MapsFileTest {

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
        return buf.toString();
    }

    @Test
    public void test1() {
        URL f = Files.getDataURL(this, "1");
        MapsFile maps = new MapsFile(f);
        Iterator<PartMap> it = maps.iterator();
        PartMap part;

        part = it.next();
        assertEquals("part A", ".=hello\nworld, age=10, name=a", map2str(part));

        part = it.next();
        assertEquals("part B", ".=BBB, age=20, location=home, name=b",
                map2str(part));

        part = it.next();
        assertEquals("part C", ".=CCC", map2str(part));

        assertFalse(it.hasNext());
        try {
            part = it.next();
            fail("extra part: " + map2str(part));
        } catch (NoSuchElementException e) {
        }
    }

}
