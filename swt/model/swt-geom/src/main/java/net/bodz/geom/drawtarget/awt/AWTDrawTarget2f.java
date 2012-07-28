package net.bodz.geom.drawtarget.awt;

import java.awt.Graphics2D;

import net.bodz.bas.util.Pair;
import net.bodz.geom.drawtarget.AbstractDrawTarget2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.IPolygon2f;

public class AWTDrawTarget2f
        extends AbstractDrawTarget2f {

    Graphics2D g;

    public AWTDrawTarget2f(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void drawPixel(float x, float y) {
        g.drawLine((int) x, (int) y, (int) x, (int) y);
    }

    @Override
    public void drawLine(float x0, float y0, float x1, float y1) {
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
    }

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1) {
        g.drawOval((int) x0, (int) y0, (int) x1, (int) y1);
    }

    @Override
    public void drawPolygon(IPolygon2f polygon) {
        if (polygon.isClosed()) {
            Pair<int[], int[]> awt = convertPolygonToAWT(polygon);
            int n = awt.first.length;
            g.drawPolygon(awt.first, awt.second, n);
            return;
        }
        try {
            super.drawPolygon(polygon);
        } catch (DrawException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = (int) x0;
        y[0] = (int) y0;
        x[1] = (int) x1;
        y[1] = (int) y1;
        x[2] = (int) x2;
        y[2] = (int) y2;
        g.fillPolygon(x, y, 3);
    }

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        g.fillOval((int) x0, (int) y0, (int) (x1 - x0), (int) (y1 - y0));
    }

    @Override
    public void fillPolygon(IPolygon2f polygon) {
        Pair<int[], int[]> awt = convertPolygonToAWT(polygon);
        int n = awt.first.length;
        g.fillPolygon(awt.first, awt.second, n);
    }

    public static Pair<int[], int[]> convertPolygonToAWT(IPolygon2f polygon) {
        int n = polygon.pointCount();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            IPoint2f point = polygon.pointRef(i);
            x[i] = (int) point.x();
            y[i] = (int) point.y();
        }
        return new Pair<int[], int[]>(x, y);
    }

}
