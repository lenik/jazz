package net.bodz.bas.gui.spec1_f;

import net.bodz.bas.geom.spec0_f.tr.ITransformedView2d;
import net.bodz.bas.geom.spec1_f.Line2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFillType;
import net.bodz.bas.gui.spec0.IFontType;
import net.bodz.bas.gui.spec0.IStrokeType;

public interface IDrawContext2d
        extends ITransformedView2d {

    // graphics attributes

    IColor getColor();

    void setColor(IColor color);

    IColor getFillColor();

    void setFillColor(IColor fillColor);

    IStrokeType getStroke();

    void setStroke(IStrokeType stroke);

    IFillType getPattern();

    void setPattern(IFillType pattern);

    IFillType getFillPattern();

    void setFillPattern(IFillType fillPattern);

    IFontType getFont();

    void setFont(IFontType font);

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
