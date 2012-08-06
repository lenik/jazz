package net.bodz.bas.geom_f.base;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.javax.vecmath.Vector2f;

public class Point2dTest
        extends Assert {

    Random rand = new Random();

    @Test
    public void testCrossProduct_Parallel() {
        for (int i = 0; i < 100; i++) {
            Point2d p = new Point2d(rand.nextFloat(), rand.nextFloat());
            Point2d q = new Point2d(rand.nextFloat(), rand.nextFloat());

            Vector2f pq = p.vectorTo(q);
            Vector2f pr = pq.clone().scale_(rand.nextFloat());

            Point2d r = p.clone().add_(pr);

            float result = p.crossProduct(q, r);
            System.out.println(result);
            assertEquals(0.0, result, 1e-5);
        }
    }

}
