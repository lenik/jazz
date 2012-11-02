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
            Point2d point1 = new Point2d(rand.nextFloat(), rand.nextFloat());
            Point2d point2 = new Point2d(rand.nextFloat(), rand.nextFloat());

            Vector2f pq = point1.vectorTo(point2);
            Vector2f pr = pq.clone().scale_(rand.nextFloat());

            Point2d r = point1.shot().add_(pr);

            float result = point1.crossProduct(point2, r);
            System.out.println(result);
            assertEquals(0.0, result, 1e-5);
        }
    }

}
