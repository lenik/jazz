package net.bodz.swt.draw.dev;

import net.bodz.geom.shape.base.ILineRef2f;
import net.bodz.geom.shape.base.IPointRef2d;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;
import net.bodz.geom.shape.base.StaticPoint2f;

public abstract class AbstractDrawTarget2f_Point
        extends AbstractDrawTarget2f {

    @Override
    public abstract void drawPixel(IPointRef2d point)
            throws DrawException;

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        drawPixel(new StaticPoint2f(x, y));
    }

    @Override
    public abstract void drawLine(IPointRef2d start, IPointRef2d end)
            throws DrawException;

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawLine(new StaticPoint2f(x0, y0), new StaticPoint2f(x1, y1));
    }

    @Override
    public void drawLine(ILineRef2f line)
            throws DrawException {
        drawLine(line.p0(), line.p1());
    }

    @Override
    public abstract void drawRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void drawRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        super.drawRectangle(x0, y0, x1, y1);
    }

    @Override
    public void drawRectangle(IRectangle2f rect)
            throws DrawException {
        super.drawRectangle(rect.p0(), rect.p1());
    }

    @Override
    public abstract void drawTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException;

    @Override
    public void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.drawTriangle(new StaticPoint2f(x0, y0), new StaticPoint2f(x1, y1), new StaticPoint2f(x2, y2));
    }

    @Override
    public void drawTriangle(ITriangle2f triangle)
            throws DrawException {
        drawTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void drawCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(new StaticPoint2f(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void drawEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawEllipse(new StaticPoint2f(x0, y0), new StaticPoint2f(x1, y1));
    }

    @Override
    public abstract void drawPolygon(IPointRef2d[] points)
            throws DrawException;

    @Override
    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPointRef2d[] points = new IPointRef2d[count];
        for (int i = 0; i < count; i++)
            points[i] = new StaticPoint2f(x[i + offset], y[i + offset]);
        drawPolygon(points);
    }

    @Override
    public void drawPolygon(IPolygon2f polygon)
            throws DrawException {
        drawPolygon(polygon.toPointRefsArray(true));
    }

    // Fill...

    @Override
    public abstract void fillRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void fillRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        super.fillRectangle(x0, y0, x1, y1);
    }

    @Override
    public void fillRectangle(IRectangle2f rect)
            throws DrawException {
        super.fillRectangle(rect.p0(), rect.p1());
    }

    @Override
    public abstract void fillTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException;

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.fillTriangle(new StaticPoint2f(x0, y0), new StaticPoint2f(x1, y1), new StaticPoint2f(x2, y2));
    }

    @Override
    public void fillTriangle(ITriangle2f triangle)
            throws DrawException {
        fillTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void fillCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(new StaticPoint2f(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void fillEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        fillEllipse(new StaticPoint2f(x0, y0), new StaticPoint2f(x1, y1));
    }

    @Override
    public abstract void fillPolygon(IPointRef2d[] points)
            throws DrawException;

    @Override
    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPointRef2d[] points = new IPointRef2d[count];
        for (int i = 0; i < count; i++)
            points[i] = new StaticPoint2f(x[i + offset], y[i + offset]);
        fillPolygon(points);
    }

    @Override
    public void fillPolygon(IPolygon2f polygon)
            throws DrawException {
        fillPolygon(polygon.toPointRefsArray(true));
    }

}
