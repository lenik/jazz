package net.bodz.geom.drawtarget;

import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Polygon2f;
import net.bodz.geom.shape.base.Rectangle2f;
import net.bodz.geom.shape.base.Triangle2f;

public abstract class AbstractDrawTarget2f_Point
        extends AbstractDrawTarget2f {

    @Override
    public abstract void drawPixel(Point2f point)
            throws DrawException;

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        drawPixel(new Point2f.Static(x, y));
    }

    @Override
    public abstract void drawLine(Point2f start, Point2f end)
            throws DrawException;

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawLine(new Point2f.Static(x0, y0), new Point2f.Static(x1, y1));
    }

    @Override
    public void drawLine(Line2f line)
            throws DrawException {
        drawLine(line.p0(), line.p1());
    }

    @Override
    public abstract void drawRectangle(Point2f p0, Point2f p1)
            throws DrawException;

    @Override
    public void drawRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        super.drawRectangle(x0, y0, x1, y1);
    }

    @Override
    public void drawRectangle(Rectangle2f rect)
            throws DrawException {
        super.drawRectangle(rect.p0(), rect.p1());
    }

    @Override
    public abstract void drawTriangle(Point2f p0, Point2f p1, Point2f p2)
            throws DrawException;

    @Override
    public void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.drawTriangle(new Point2f.Static(x0, y0), new Point2f.Static(x1, y1), new Point2f.Static(x2, y2));
    }

    @Override
    public void drawTriangle(Triangle2f triangle)
            throws DrawException {
        drawTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void drawCircle(Point2f center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(new Point2f.Static(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void drawEllipse(Point2f p0, Point2f p1)
            throws DrawException;

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawEllipse(new Point2f.Static(x0, y0), new Point2f.Static(x1, y1));
    }

    @Override
    public abstract void drawPolygon(Point2f[] points)
            throws DrawException;

    @Override
    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        Point2f[] points = new Point2f[count];
        for (int i = 0; i < count; i++)
            points[i] = new Point2f.Static(x[i + offset], y[i + offset]);
        drawPolygon(points);
    }

    @Override
    public void drawPolygon(Polygon2f polygon)
            throws DrawException {
        drawPolygon(polygon.toPointRefsArray(true));
    }

    // Fill...

    @Override
    public abstract void fillRectangle(Point2f p0, Point2f p1)
            throws DrawException;

    @Override
    public void fillRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        super.fillRectangle(x0, y0, x1, y1);
    }

    @Override
    public void fillRectangle(Rectangle2f rect)
            throws DrawException {
        super.fillRectangle(rect.p0(), rect.p1());
    }

    @Override
    public abstract void fillTriangle(Point2f p0, Point2f p1, Point2f p2)
            throws DrawException;

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.fillTriangle(new Point2f.Static(x0, y0), new Point2f.Static(x1, y1), new Point2f.Static(x2, y2));
    }

    @Override
    public void fillTriangle(Triangle2f triangle)
            throws DrawException {
        fillTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void fillCircle(Point2f center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(new Point2f.Static(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void fillEllipse(Point2f p0, Point2f p1)
            throws DrawException;

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        fillEllipse(new Point2f.Static(x0, y0), new Point2f.Static(x1, y1));
    }

    @Override
    public abstract void fillPolygon(Point2f[] points)
            throws DrawException;

    @Override
    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        Point2f[] points = new Point2f[count];
        for (int i = 0; i < count; i++)
            points[i] = new Point2f.Static(x[i + offset], y[i + offset]);
        fillPolygon(points);
    }

    @Override
    public void fillPolygon(Polygon2f polygon)
            throws DrawException {
        fillPolygon(polygon.toPointRefsArray(true));
    }

}
