package net.bodz.swt.controls.gs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import net.bodz.bas.types.util.Arrays2;
import net.bodz.swt.controls.gs.GridSpace.HFirst;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.junit.Test;

public class GridSpaceTest {

    @Test
    public void testHFirst() {
        HFirst gs = new HFirst(4, 4);
        gs.setCellWidth(20);
        gs.setCellHeight(10);
        Rectangle b = gs.getBounds();
        assertEquals(80, b.width);
        assertEquals(40, b.height);
        gs.setCellSize(10);

        gs.setHPadding(1);
        gs.setVPadding(2);
        b = gs.getBounds();
        assertEquals(43, b.width);
        assertEquals(46, b.height);
        gs.setPadding(1);

        gs.setCellWidth(9);
        gs.setCellHeight(9);
        Point p = gs.getOrig(0);
        assertEquals(0, p.x);
        assertEquals(0, p.y);
        p = gs.getOrig(7);
        assertEquals(30, p.x);
        assertEquals(10, p.y);

        assertEquals(2 * 4 + 1, gs.find(13, 22));// cell(2, 1)
        assertEquals(-1, gs.find(19, 22));// cell(2, 1+)
        assertEquals(-1, gs.find(13, 29));// cell(2+,1)
        assertEquals(3 * 4 + 2, gs.find(23, 31));// cell(3, 2)
        assertEquals(3 * 4 + 3, gs.find(38, 38));// cell(3, 3)
        assertEquals(-1, gs.find(40, 40));// cell(4, 4)

        // [2, 2] - [3, 3]
        Iterator<Integer> it = gs.iterator(22, 22, 33, 33);
        int[] found = Arrays2.convert(it);
        int[] exp1 = {
        //
                2 * 4 + 2, //
                2 * 4 + 3, //
                3 * 4 + 2, //
                3 * 4 + 3, //
        };
        assertArrayEquals(exp1, found);
    }

}
