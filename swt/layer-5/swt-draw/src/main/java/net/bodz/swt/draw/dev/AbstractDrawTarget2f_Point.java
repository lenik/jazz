package net.bodz.swt.draw.dev;

import net.bodz.bas.geom_f.base.ILine2d;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.IPolygon2d;
import net.bodz.bas.geom_f.base.IRectangle2d;
import net.bodz.bas.geom_f.base.ITriangle2d;
import net.bodz.bas.geom_f.base.Point2d;

public abstract class AbstractDrawTarget2f_Point
        extends AbstractDrawTarget2f {

    @Override
    public abstract void drawPixel(IPointRef2d point)
            throws DrawException;

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        drawPixel(new Point2d(x, y));
    }

    @Override
    public abstract void drawLine(IPointRef2d start, IPointRef2d end)
            throws DrawException;

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawLine(new Point2d(x0, y0), new Point2d(x1, y1));
    }

    @Override
    public void drawLine(ILine2d line)
            throws DrawException {
        drawLine(line.getP0(), line.getP1());
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
    public void drawRectangle(IRectangle2d rect)
            throws DrawException {
        super.drawRectangle(rect.getPoint0(), rect.getPoint1());
    }

    @Override
    public abstract void drawTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException;

    @Override
    public void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.drawTriangle(new Point2d(x0, y0), new Point2d(x1, y1), new Point2d(x2, y2));
    }

    @Override
    public void drawTriangle(ITriangle2d triangle)
            throws DrawException {
        drawTriangle(triangle.getPoint0(), triangle.getPoint1(), triangle.getPoint2());
    }

    @Override
    public abstract void drawCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(new Point2d(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void drawEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawEllipse(new Point2d(x0, y0), new Point2d(x1, y1));
    }

    @Override
    public abstract void drawPolygon(IPointRef2d[] points)
            throws DrawException;

    @Override
    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPointRef2d[] points = new IPointRef2d[count];
        for (int i = 0; i < count; i++)
            points[i] = new Point2d(x[i + offset], y[i + offset]);
        drawPolygon(points);
    }

    @Override
    public void drawPolygon(IPolygon2d polygon)
            throws DrawException {
        drawPolygon(polygon.toPointRefArray(true));
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
    public void fillRectangle(IRectangle2d rect)
            throws DrawException {
        super.fillRectangle(rect.getPoint0(), rect.getPoint1());
    }

    @Override
    public abstract void fillTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException;

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        super.fillTriangle(new Point2d(x0, y0), new Point2d(x1, y1), new Point2d(x2, y2));
    }

    @Override
    public void fillTriangle(ITriangle2d triangle)
            throws DrawException {
        fillTriangle(triangle.getPoint0(), triangle.getPoint1(), triangle.getPoint2());
    }

    @Override
    public abstract void fillCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException;

    @Override
    public final void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(new Point2d(centerX, centerY), radiusX, radiusY);
    }

    @Override
    public abstract void fillEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException;

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        fillEllipse(new Point2d(x0, y0), new Point2d(x1, y1));
    }

    @Override
    public abstract void fillPolygon(IPointRef2d[] points)
            throws DrawException;

    @Override
    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPointRef2d[] points = new IPointRef2d[count];
        for (int i = 0; i < count; i++)
            points[i] = new Point2d(x[i + offset], y[i + offset]);
        fillPolygon(points);
    }

    @Override
    public void fillPolygon(IPolygon2d polygon)
            throws DrawException {
        fillPolygon(polygon.toPointRefArray(true));
    }

}
