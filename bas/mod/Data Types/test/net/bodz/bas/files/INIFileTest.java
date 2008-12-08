package net.bodz.bas.files;

import static net.bodz.bas.files.MapsFileTest.map2str;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.TextMap;

import org.junit.Test;

public class INIFileTest {

    URL f = Files.getDataURL(this, "1");

    @Test
    public void testMerge() {
        INIFile file = new INIFile(f, true);
        TextMap<String> all = file.iterator().next();
        assertEquals("a=1, b=2, c=hello, world!"
                + ", him.exist=true, him.location=home" //
                + ", you.age=[what], you.exist=true, you.name=hi", //
                map2str(all));
    }

    @Test
    public void test1() {
        INIFile file = new INIFile(f);
        Iterator<TextMap<String>> iter = file.iterator();

        TextMap<String> p1 = iter.next();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1));

        TextMap<String> p2 = iter.next();
        assertEquals("age=[what], name=hi", map2str(p2));

        TextMap<String> p3 = iter.next();
        assertEquals("location=home", map2str(p3));

        assertFalse(iter.hasNext());
    }

}
