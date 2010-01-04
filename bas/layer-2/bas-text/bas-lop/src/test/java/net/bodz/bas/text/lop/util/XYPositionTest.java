package net.bodz.bas.text.lop.util;

import static org.junit.Assert.assertEquals;

import net.bodz.bas.text.lop.util.XYPosition;

import org.junit.Test;

public class XYPositionTest {

    @Test
    public void testAdd() throws Exception {
        XYPosition start = new XYPosition(100, 20, 30);
        assertEquals(new XYPosition(100, 20, 30), start.add(""));
        assertEquals(new XYPosition(105, 20, 35), start.add("hello"));
        assertEquals(new XYPosition(101, 21, 0), start.add("\n"));
        assertEquals(new XYPosition(103, 22, 0), start.add("\r\n\r"));
        assertEquals(new XYPosition(110, 21, 5), start.add("aaaa\n12345"));
    }

}
