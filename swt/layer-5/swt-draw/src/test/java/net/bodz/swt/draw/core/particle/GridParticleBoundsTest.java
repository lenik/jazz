package net.bodz.swt.draw.core.particle;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.util.ints.IntIterable;
import net.bodz.swt.draw.core.particle.GridParticleBounds.HFirst;

public class GridParticleBoundsTest
        extends Assert {

    @Test
    public void testHFirst() {
        HFirst hFirst = new HFirst(4, 4);
        hFirst.setCellWidth(20);
        hFirst.setCellHeight(10);
        Rectangle b = hFirst.getBoundingBox();
        assertEquals(80, b.width);
        assertEquals(40, b.height);
        hFirst.setCellSize(10);

        hFirst.setHPadding(1);
        hFirst.setVPadding(2);
        b = hFirst.getBoundingBox();
        assertEquals(43, b.width);
        assertEquals(46, b.height);
        hFirst.setPadding(1);

        hFirst.setCellWidth(9);
        hFirst.setCellHeight(9);
        Point p = hFirst.getOrig(0);
        assertEquals(0, p.x);
        assertEquals(0, p.y);
        p = hFirst.getOrig(7);
        assertEquals(30, p.x);
        assertEquals(10, p.y);

        assertEquals(2 * 4 + 1, hFirst.getParticleIndexAt(13, 22));// cell(2, 1)
        assertEquals(-1, hFirst.getParticleIndexAt(19, 22));// cell(2, 1+)
        assertEquals(-1, hFirst.getParticleIndexAt(13, 29));// cell(2+,1)
        assertEquals(3 * 4 + 2, hFirst.getParticleIndexAt(23, 31));// cell(3, 2)
        assertEquals(3 * 4 + 3, hFirst.getParticleIndexAt(38, 38));// cell(3, 3)
        assertEquals(-1, hFirst.getParticleIndexAt(40, 40));// cell(4, 4)

        // [2, 2] - [3, 3]
        IntIterable particles = hFirst.getParticleIndexes(new Rectangle(22, 22, 10, 10));
        int[] found = Arrays.convert(particles);
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
