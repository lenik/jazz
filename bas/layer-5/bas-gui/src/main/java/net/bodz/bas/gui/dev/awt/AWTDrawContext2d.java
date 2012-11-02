package net.bodz.bas.gui.dev.awt;

import java.awt.Graphics2D;

import net.bodz.bas.geom_f.base.*;
import net.bodz.bas.gui.dev.AbstractDrawContext2d;
import net.bodz.bas.util.Pair;

public class AWTDrawContext2d
        extends AbstractDrawContext2d {

    Graphics2D g;

    public AWTDrawContext2d(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void drawPixel(int x, int y) {
        g.drawLine(x, y, x, y);
    }

    @Override
    public void drawPixel(float x, float y) {
        g.drawLine((int) x, (int) y, (int) x, (int) y);
    }

    @Override
    public void drawPixel(Point2d point) {
        drawPixel(point.x, point.y);
    }

    @Override
    public void drawLine(Point2d start, Point2d end) {
        g.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

    @Override
    public void drawLine(Line2d line) {
        drawLine(line.p0, line.p1);
    }

    @Override
    public void drawRectangle(Rectangle2d rect) {
        Point2d p0 = rect.getPoint0();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        g.drawRect((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void fillRectangle(Rectangle2d rect) {
        Point2d p0 = rect.getPoint0();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        g.fillRect((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void drawTriangle(Triangle2d triangle) {
        // TODO triangle.toPolygon();
    }

    @Override
    public void fillTriangle(Triangle2d triangle) {
        // TODO triangle.toPolygon();
    }

    @Override
    public void drawPolygon(Polygon2d polygon) {
        Pair<int[], int[]> awt = convertPolygonToAWT(polygon);
        int n = awt.first.length;

        if (polygon.isClosed())
            g.drawPolygon(awt.first, awt.second, n);
        else
            g.drawPolyline(awt.first, awt.second, n);
    }

    @Override
    public void fillPolygon(Polygon2d polygon) {
        Pair<int[], int[]> awt = convertPolygonToAWT(polygon);
        int n = awt.first.length;
        g.fillPolygon(awt.first, awt.second, n);
    }

    @Override
    public void drawCircle(Point2d center, float radius) {
        float x0 = center.x - radius;
        float y0 = center.y - radius;
        int d = (int) (radius * 2);
        g.drawOval((int) x0, (int) y0, d, d);
    }

    @Override
    public void fillCircle(Point2d center, float radius) {
        float x0 = center.x - radius;
        float y0 = center.y - radius;
        int d = (int) (radius * 2);
        g.fillOval((int) x0, (int) y0, d, d);
    }

    @Override
    public void drawEllipse(Rectangle2d bbox) {
        Rectangle2d posi = bbox.shot();
        posi.positize();
        Point2d p0 = posi.getPoint0();
        int width = (int) posi.getWidth();
        int height = (int) posi.getHeight();
        g.drawOval((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void fillEllipse(Rectangle2d bbox) {
        Rectangle2d posi = bbox.shot();
        posi.positize();
        Point2d p0 = posi.getPoint0();
        int width = (int) posi.getWidth();
        int height = (int) posi.getHeight();
        g.fillOval((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void drawEllipse(Point2d center, float radiusX, float radiusY) {
        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        int dx = (int) (radiusX * 2);
        int dy = (int) (radiusY * 2);
        g.drawOval((int) x0, (int) y0, dx, dy);
    }

    @Override
    public void fillEllipse(Point2d center, float radiusX, float radiusY) {
        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        int dx = (int) (radiusX * 2);
        int dy = (int) (radiusY * 2);
        g.fillOval((int) x0, (int) y0, dx, dy);
    }

    public static Pair<int[], int[]> convertPolygonToAWT(IPolygon2d polygon) {
        int n = polygon.getPointCount();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            IPointRef2d point = polygon.getPointRef(i);
            x[i] = (int) point.getX();
            y[i] = (int) point.getY();
        }
        return new Pair<int[], int[]>(x, y);
    }

}
