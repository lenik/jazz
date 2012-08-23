package net.bodz.bas.gui.dev;

import net.bodz.bas.geom_f.base.AbstractRectangle2d;
import net.bodz.bas.geom_f.base.Line2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.Polygon2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.geom_f.base.Triangle2d;

public class DirtyAreaDrawContext2d
        extends DecoratedDrawContext2d {

    private static final long serialVersionUID = 1L;
    private AbstractRectangle2d dirtyRect;

    public DirtyAreaDrawContext2d(IDrawContext2d _orig) {
        super(_orig);
        clearDirty();
    }

    private void clearDirty() {
        dirtyRect = Rectangle2d.sized(//
                Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);
    }

    @Override
    public void drawPixel(Point2d point)
            throws GraphicsOperationException {
        super.drawPixel(point);
        dirtyRect.include(point);
    }

    @Override
    public void drawLine(Point2d start, Point2d end)
            throws GraphicsOperationException {
        super.drawLine(start, end);
        dirtyRect.include(start);
        dirtyRect.include(end);
    }

    @Override
    public void drawLine(Line2d line)
            throws GraphicsOperationException {
        super.drawLine(line);
        dirtyRect.include(line.getPoint0());
        dirtyRect.include(line.getPoint1());
    }

    @Override
    public void drawRectangle(Rectangle2d rect)
            throws GraphicsOperationException {
        super.drawRectangle(rect);
        dirtyRect.include(rect.getPoint0());
        dirtyRect.include(rect.getPoint2());
    }

    @Override
    public void fillRectangle(Rectangle2d rect)
            throws GraphicsOperationException {
        super.fillRectangle(rect);
        dirtyRect.include(rect.getPoint0());
        dirtyRect.include(rect.getPoint2());
    }

    @Override
    public void drawTriangle(Triangle2d triangle)
            throws GraphicsOperationException {
        super.drawTriangle(triangle);
        dirtyRect.include(triangle.getPoint0());
        dirtyRect.include(triangle.getPoint1());
        dirtyRect.include(triangle.getPoint2());
    }

    @Override
    public void fillTriangle(Triangle2d triangle)
            throws GraphicsOperationException {
        super.fillTriangle(triangle);
        dirtyRect.include(triangle.getPoint0());
        dirtyRect.include(triangle.getPoint1());
        dirtyRect.include(triangle.getPoint2());
    }

    @Override
    public void drawPolygon(Polygon2d polygon)
            throws GraphicsOperationException {
        super.drawPolygon(polygon);
        for (Point2d point : polygon.getPoints())
            dirtyRect.include(point);
    }

    @Override
    public void fillPolygon(Polygon2d polygon)
            throws GraphicsOperationException {
        super.fillPolygon(polygon);
        for (Point2d point : polygon.getPoints())
            dirtyRect.include(point);
    }

    @Override
    public void drawCircle(Point2d center, float radius)
            throws GraphicsOperationException {
        super.drawCircle(center, radius);

        float x0 = center.x - radius;
        float y0 = center.y - radius;
        float d = radius * 2;
        dirtyRect.include(x0, y0);
        dirtyRect.include(x0 + d, y0 + d);
    }

    @Override
    public void fillCircle(Point2d center, float radius)
            throws GraphicsOperationException {
        super.fillCircle(center, radius);

        float x0 = center.x - radius;
        float y0 = center.y - radius;
        float d = radius * 2;
        dirtyRect.include(x0, y0);
        dirtyRect.include(x0 + d, y0 + d);
    }

    @Override
    public void drawEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException {
        super.drawEllipse(center, radiusX, radiusY);

        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        dirtyRect.include(x0, y0);
        dirtyRect.include(x0 + radiusX * 2, y0 + radiusY * 2);
    }

    @Override
    public void fillEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException {
        super.fillEllipse(center, radiusX, radiusY);

        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        dirtyRect.include(x0, y0);
        dirtyRect.include(x0 + radiusX * 2, y0 + radiusY * 2);
    }

    @Override
    public void drawEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException {
        super.drawEllipse(boundingBox);
        dirtyRect.include(boundingBox);
    }

    @Override
    public void fillEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException {
        super.fillEllipse(boundingBox);
        dirtyRect.include(boundingBox);
    }

}
