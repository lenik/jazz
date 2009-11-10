package net.bodz.bas.files;

import static net.bodz.bas.files.PartRecordsTest.map2str;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.util.DirectIterator;

import org.junit.Test;

public class INIRecordsTest {

    ResLink resLink;

    public INIRecordsTest() {
        URL classData = Files.classData(getClass(), "1"); //$NON-NLS-1$
        resLink = new URLResLink(classData);
    }

    @Test
    public void testByGroup() throws IOException {
        INIRecords records = new INIRecords(resLink, false);
        DirectIterator<? extends TextMap<String>, IOException> iter = records.iterator();

        TextMap<String> p1 = iter.getNext();
        assertEquals("a=1, b=2, c=hello, world!", map2str(p1)); //$NON-NLS-1$

        TextMap<String> p2 = iter.getNext();
        assertEquals("_section=you, age=[what], name=hi", map2str(p2)); //$NON-NLS-1$

        TextMap<String> p3 = iter.getNext();
        assertEquals("_section=him, location=home", map2str(p3)); //$NON-NLS-1$

        assertFalse(iter.next());
    }

    @Test
    public void testFlatten() throws IOException {
        INIRecords records = new INIRecords(resLink, true);
        TextMap<String> all = records.iterator().getNext();
        assertEquals("a=1, b=2, c=hello, world!" //$NON-NLS-1$
                + ", him.exist=true, him.location=home" // //$NON-NLS-1$
                + ", you.age=[what], you.exist=true, you.name=hi", // //$NON-NLS-1$
                map2str(all));
    }

}
