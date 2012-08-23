package net.bodz.bas.gui.dev;

import net.bodz.bas.geom_f.base.Line2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.Polygon2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.geom_f.base.Triangle2d;
import net.bodz.bas.geom_f.tr.ITransformedView2d;

public interface IDrawContext2d
        extends ITransformedView2d {

    // graphics attributes

    IColor getColor();

    void setColor(IColor color);

    IColor getFillColor();

    void setFillColor(IColor fillColor);

    IStroke getStroke();

    void setStroke(IStroke stroke);

    IFillPattern getPattern();

    void setPattern(IFillPattern pattern);

    IFillPattern getFillPattern();

    void setFillPattern(IFillPattern fillPattern);

    IFont getFont();

    void setFont(IFont font);

    void drawPixel(int x, int y)
            throws GraphicsOperationException;

    void drawPixel(float x, float y)
            throws GraphicsOperationException;

    void drawPixel(Point2d point)
            throws GraphicsOperationException;

    void drawLine(Point2d start, Point2d end)
            throws GraphicsOperationException;

    void drawLine(Line2d line)
            throws GraphicsOperationException;

    void drawRectangle(Rectangle2d rect)
            throws GraphicsOperationException;

    void drawTriangle(Triangle2d triangle)
            throws GraphicsOperationException;

    void drawCircle(Point2d center, float radius)
            throws GraphicsOperationException;

    void drawEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException;

    void drawEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException;

    void drawPolygon(Polygon2d polygon)
            throws GraphicsOperationException;

    void fillRectangle(Rectangle2d rect)
            throws GraphicsOperationException;

    void fillTriangle(Triangle2d triangle)
            throws GraphicsOperationException;

    void fillCircle(Point2d center, float radius)
            throws GraphicsOperationException;

    void fillEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException;

    void fillEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException;

    void fillPolygon(Polygon2d polygon)
            throws GraphicsOperationException;

}