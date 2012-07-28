package net.bodz.geom.drawtarget;

import net.bodz.geom.shape.base.ILine2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;

public abstract class AbstractDrawTarget2f_Point
        extends AbstractDrawTarget2f {

    @Override
    public abstract void drawPixel(IPoint2f point)
            throws DrawException;

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        drawPixel(new IPoint2f.Static(x, y));
    }

    @Override
    public abstract void drawLine(IPoint2f start, IPoint2f end)
            throws DrawException;

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawLine(new IPoint2f.Static(x0, y0), new IPoint2f.Static(x1, y1));
    }

    @Override
    public void drawLine(ILine2f line)
            throws DrawException {
        drawLine(line.p0(), line.p1());
    }

    @Override
    public abstract void drawRectangle(IPoint2f p0, IPoint2f p1)
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
    public abstract void drawTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException;

    @Override
    public void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.drawTriangle(new IPoint2f.Static(x0, y0), new IPoint2f.Static(x1, y1), new IPoint2f.Static(x2, y2));
    }

    @Override
    public void drawTriangle(ITriangle2f triangle)
            throws DrawException {
        drawTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void drawCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(new IPoint2f.Static(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void drawEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawEllipse(new IPoint2f.Static(x0, y0), new IPoint2f.Static(x1, y1));
    }

    @Override
    public abstract void drawPolygon(IPoint2f[] points)
            throws DrawException;

    @Override
    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPoint2f[] points = new IPoint2f[count];
        for (int i = 0; i < count; i++)
            points[i] = new IPoint2f.Static(x[i + offset], y[i + offset]);
        drawPolygon(points);
    }

    @Override
    public void drawPolygon(IPolygon2f polygon)
            throws DrawException {
        drawPolygon(polygon.toPointRefsArray(true));
    }

    // Fill...

    @Override
    public abstract void fillRectangle(IPoint2f p0, IPoint2f p1)
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
    public abstract void fillTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException;

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.fillTriangle(new IPoint2f.Static(x0, y0), new IPoint2f.Static(x1, y1), new IPoint2f.Static(x2, y2));
    }

    @Override
    public void fillTriangle(ITriangle2f triangle)
            throws DrawException {
        fillTriangle(triangle.p0(), triangle.p1(), triangle.p2());
    }

    @Override
    public abstract void fillCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(new IPoint2f.Static(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void fillEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        fillEllipse(new IPoint2f.Static(x0, y0), new IPoint2f.Static(x1, y1));
    }

    @Override
    public abstract void fillPolygon(IPoint2f[] points)
            throws DrawException;

    @Override
    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPoint2f[] points = new IPoint2f[count];
        for (int i = 0; i < count; i++)
            points[i] = new IPoint2f.Static(x[i + offset], y[i + offset]);
        fillPolygon(points);
    }

    @Override
    public void fillPolygon(IPolygon2f polygon)
            throws DrawException {
        fillPolygon(polygon.toPointRefsArray(true));
    }

}
