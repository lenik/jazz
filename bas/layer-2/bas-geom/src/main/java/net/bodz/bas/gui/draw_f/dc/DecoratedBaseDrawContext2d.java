package net.bodz.bas.gui.draw_f.dc;

import net.bodz.bas.geom.spec0_f.tr.DecoratedTransformedView2d;
import net.bodz.bas.geom.spec0_f.tr.ITransformedView2d;
import net.bodz.bas.geom.spec1_f.Line2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;

public class DecoratedBaseDrawContext2d
        extends DecoratedTransformedView2d
        implements IBaseDrawContext2d {

    private static final long serialVersionUID = 1L;

    public DecoratedBaseDrawContext2d(ITransformedView2d _orig) {
        super(_orig);
    }

    @Override
    public IBaseDrawContext2d getWrapped() {
        return (IBaseDrawContext2d) _orig;
    }

    @Override
    public void drawPixel(int x, int y)
            throws DrawException {
        getWrapped().drawPixel(x, y);
    }

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        getWrapped().drawPixel(x, y);
    }

    @Override
    public void drawPixel(Point2d point)
            throws DrawException {
        getWrapped().drawPixel(point);
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1)
            throws DrawException {
        getWrapped().drawLine(x0, y0, x1, y1);
    }

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        getWrapped().drawLine(x0, y0, x1, y1);
    }

    @Override
    public void drawLine(Point2d start, Point2d end)
            throws DrawException {
        getWrapped().drawLine(start, end);
    }

    @Override
    public void drawLine(Line2d line)
            throws DrawException {
        getWrapped().drawLine(line);
    }

    @Override
    public void drawRectangle(Rectangle2d rect)
            throws DrawException {
        getWrapped().drawRectangle(rect);
    }

    @Override
    public void drawTriangle(Triangle2d triangle)
            throws DrawException {
        getWrapped().drawTriangle(triangle);
    }

    @Override
    public void drawCircle(Point2d center, float radius)
            throws DrawException {
        getWrapped().drawCircle(center, radius);
    }

    @Override
    public void drawEllipse(Rectangle2d boundingBox)
            throws DrawException {
        getWrapped().drawEllipse(boundingBox);
    }

    @Override
    public void drawEllipse(Point2d center, float radiusX, float radiusY)
            throws DrawException {
        getWrapped().drawEllipse(center, radiusX, radiusY);
    }

    @Override
    public void drawPolygon(Polygon2d polygon)
            throws DrawException {
        getWrapped().drawPolygon(polygon);
    }

    @Override
    public void fillRectangle(Rectangle2d rect)
            throws DrawException {
        getWrapped().fillRectangle(rect);
    }

    @Override
    public void fillTriangle(Triangle2d triangle)
            throws DrawException {
        getWrapped().fillTriangle(triangle);
    }

    @Override
    public void fillCircle(Point2d center, float radius)
            throws DrawException {
        getWrapped().fillCircle(center, radius);
    }

    @Override
    public void fillEllipse(Rectangle2d boundingBox)
            throws DrawException {
        getWrapped().fillEllipse(boundingBox);
    }

    @Override
    public void fillEllipse(Point2d center, float radiusX, float radiusY)
            throws DrawException {
        getWrapped().fillEllipse(center, radiusX, radiusY);
    }

    @Override
    public void fillPolygon(Polygon2d polygon)
            throws DrawException {
        getWrapped().fillPolygon(polygon);
    }

}
