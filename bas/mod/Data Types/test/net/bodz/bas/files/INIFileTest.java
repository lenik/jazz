package net.bodz.bas.files;

import static net.bodz.bas.files.MapsFileTest.map2str;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.net.URL;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.TextMap;

import org.junit.Test;

public class INIFileTest {

    URL f = Files.classData(getClass(), "1"); //$NON-NLS-1$

    @Test
    public void testMerge() {
        INIFile file = new INIFile(f, true);
        TextMap<String> all = file.iterator().next();
        assertEquals("a=1, b=2, c=hello, world!" //$NON-NLS-1$
                + ", him.exist=true, him.location=home" // //$NON-NLS-1$
                + ", you.age=[what], you.exist=true, you.name=hi", // //$NON-NLS-1$
                map2str(all));
    }

    @Test
    public void test1() {
        INIFile file = new INIFile(f);
        Iterator<TextMap<String>> iter = file.iterator();

        TextMap<String> p1 = iter.next();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1)); //$NON-NLS-1$

        TextMap<String> p2 = iter.next();
        assertEquals("age=[what], name=hi", map2str(p2)); //$NON-NLS-1$

        TextMap<String> p3 = iter.next();
        assertEquals("location=home", map2str(p3)); //$NON-NLS-1$

        assertFalse(iter.hasNext());
    }

}
