package net.bodz.bas.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.commons.iterators.DirectIterator;
import net.bodz.bas.files.PartRecords.PartMap;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;

import org.junit.Test;

public class PartRecordsTest {

    static String map2str(Map<String, String> map) {
        if (map == null)
            return "(null)"; //$NON-NLS-1$
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        StringBuffer buf = null;
        for (String k : keys) {
            if (buf == null)
                buf = new StringBuffer();
            else
                buf.append(", "); //$NON-NLS-1$
            String v = map.get(k);
            buf.append(k + "=" + v); //$NON-NLS-1$
        }
        if (buf == null)
            return ""; //$NON-NLS-1$
        return buf.toString().replace("\r", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void test1() throws IOException {
        URL url = Files.classData(getClass(), "1"); //$NON-NLS-1$
        ResLink resLink = new URLResLink(url);
        PartRecords maps = new PartRecords(resLink);
        DirectIterator<PartMap, IOException> it = maps.iterator();
        PartMap part;

        part = it.getNext();
        assertEquals("part A", ".=hello\nworld\n, age=10, name=a", //$NON-NLS-1$ //$NON-NLS-2$
                map2str(part));

        part = it.getNext();
        assertEquals("part B", ".=BBB\n, age=20, location=home\n, name=b", //$NON-NLS-1$ //$NON-NLS-2$
                map2str(part));

        part = it.getNext();
        assertEquals("part C", ".=CCC\n", map2str(part)); //$NON-NLS-1$ //$NON-NLS-2$

        part = it.getNext();
        assertEquals("part D", ".=DDD\n, name=d", map2str(part)); //$NON-NLS-1$ //$NON-NLS-2$

        part = it.getNext();
        assertEquals("part E", ".=EEE\nFFF\n", map2str(part)); //$NON-NLS-1$ //$NON-NLS-2$

        assertFalse(it.next());
        try {
            part = it.get();
            fail("extra part: " + map2str(part)); //$NON-NLS-1$
        } catch (NoSuchElementException e) {
        }
    }

}
