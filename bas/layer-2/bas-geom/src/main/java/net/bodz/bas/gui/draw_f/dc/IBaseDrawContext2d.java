package net.bodz.bas.gui.draw_f.dc;

import net.bodz.bas.geom.spec0_f.tr.ITransformedView2d;
import net.bodz.bas.geom.spec1_f.Line2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;

public interface IBaseDrawContext2d
        extends ITransformedView2d {

    void drawPixel(int x, int y)
            throws DrawException;

    void drawPixel(float x, float y)
            throws DrawException;

    void drawPixel(Point2d point)
            throws DrawException;

    void drawLine(int x0, int y0, int x1, int y1)
            throws DrawException;

    void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawLine(Point2d start, Point2d end)
            throws DrawException;

    void drawLine(Line2d line)
            throws DrawException;

    void drawRectangle(Rectangle2d rect)
            throws DrawException;

    void drawTriangle(Triangle2d triangle)
            throws DrawException;

    void drawCircle(Point2d center, float radius)
            throws DrawException;

    void drawEllipse(Rectangle2d boundingBox)
            throws DrawException;

    void drawEllipse(Point2d center, float radiusX, float radiusY)
            throws DrawException;

    void drawPolygon(Polygon2d polygon)
            throws DrawException;

    void fillRectangle(Rectangle2d rect)
            throws DrawException;

    void fillTriangle(Triangle2d triangle)
            throws DrawException;

    void fillCircle(Point2d center, float radius)
            throws DrawException;

    void fillEllipse(Rectangle2d boundingBox)
            throws DrawException;

    void fillEllipse(Point2d center, float radiusX, float radiusY)
            throws DrawException;

    void fillPolygon(Polygon2d polygon)
            throws DrawException;

}
