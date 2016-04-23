package net.bodz.bas.fmt.records;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.res.builtin.URLResource;

public class TextRecordsTest
        extends Assert {

    @Test
    public void testParse1() {
        URLResource data = ClassResource.getData(getClass(), "1.txts");
        TextRecords textRecords = new TextRecords(data);
        String defaultPartName = textRecords.getDefaultPartName();

        Map<String, String> parts = textRecords.toMap();
        Iterator<Entry<String, String>> it = parts.entrySet().iterator();

        Entry<String, String> part0 = it.next();
        assertEquals(defaultPartName, part0.getKey());
        assertEquals("  dat 0  \n", part0.getValue());

        Entry<String, String> part1 = it.next();
        assertEquals("part1", part1.getKey());
        assertEquals("\ndat 1. ", part1.getValue());

        Entry<String, String> part2 = it.next();
        assertEquals("part2", part2.getKey());
        assertEquals("\n dat2", part2.getValue());
    }

    @Test
    public void testParseSpecial() {
        URLResource data = ClassResource.getData(getClass(), "2.txts");
        TextRecords textRecords = new TextRecords(data);
        String defaultPartName = textRecords.getDefaultPartName();

        Map<String, String> parts = textRecords.toMap();
        Iterator<Entry<String, String>> it = parts.entrySet().iterator();

        Entry<String, String> part0 = it.next();
        assertEquals(defaultPartName, part0.getKey());
        assertEquals("  <-- not\npart-->\n", part0.getValue());

        Entry<String, String> part1 = it.next();
        assertEquals("part1", part1.getKey());
        assertEquals("\nA<--foo-->B", part1.getValue());
    }

}
