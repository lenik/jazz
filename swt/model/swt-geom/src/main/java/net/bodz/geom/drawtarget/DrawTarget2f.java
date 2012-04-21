package net.bodz.geom.drawtarget;

import net.bodz.geom.shape.base.Line2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Polygon2f;
import net.bodz.geom.shape.base.Rectangle2f;
import net.bodz.geom.shape.base.Triangle2f;

public interface DrawTarget2f {

    void drawPixel(float x, float y)
            throws DrawException;

    void drawPixel(Point2f point)
            throws DrawException;

    void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawLine(Point2f start, Point2f end)
            throws DrawException;

    void drawLine(Line2f line)
            throws DrawException;

    void drawRectangle(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawRectangle(Point2f p0, Point2f p1)
            throws DrawException;

    void drawRectangle(Rectangle2f rect)
            throws DrawException;

    void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException;

    void drawTriangle(Point2f p0, Point2f p1, Point2f p2)
            throws DrawException;

    void drawTriangle(Triangle2f triangle)
            throws DrawException;

    void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawEllipse(Point2f p0, Point2f p1)
            throws DrawException;

    void drawEllipse(Rectangle2f boundingBox)
            throws DrawException;

    // void drawEllipse(Ellipse2f ellipse);
    void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException;

    void drawCircle(float centerX, float centerY, float radius)
            throws DrawException;

    void drawCircle(Point2f center, float radiusX, float radiusY)
            throws DrawException;

    void drawCircle(Point2f center, float radius)
            throws DrawException;

    void drawPolygon(Polygon2f polygon)
            throws DrawException;

    void drawPolygon(Point2f[] points)
            throws DrawException;

    void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException;

    void drawPolygon(float[] x, float[] y)
            throws DrawException;

    void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException;

    void fillTriangle(Point2f p0, Point2f p1, Point2f p2)
            throws DrawException;

    void fillTriangle(Triangle2f triangle)
            throws DrawException;

    void fillRectangle(float x0, float y0, float x1, float y1)
            throws DrawException;

    void fillRectangle(Point2f p0, Point2f p1)
            throws DrawException;

    void fillRectangle(Rectangle2f boundingBox)
            throws DrawException;

    void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException;

    void fillEllipse(Point2f p0, Point2f p1)
            throws DrawException;

    void fillEllipse(Rectangle2f boundingBox)
            throws DrawException;

    // void drawFilledEllipse(Ellipse2f ellipse);
    void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException;

    void fillCircle(float centerX, float centerY, float radius)
            throws DrawException;

    void fillCircle(Point2f center, float radiusX, float radiusY)
            throws DrawException;

    void fillCircle(Point2f center, float radius)
            throws DrawException;

    void fillPolygon(Polygon2f polygon)
            throws DrawException;

    void fillPolygon(Point2f[] points)
            throws DrawException;

    void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException;

    void fillPolygon(float[] x, float[] y)
            throws DrawException;

    // Styles

    Stroke getStroke();

    Color getColor();

    Color getFillColor();

    Color getFontColor();

    Pattern getPattern();

    Pattern getFillPattern();

    Pattern getFontPattern();

    Font getFont();

    void setStroke(Stroke stroke);

    void setColor(Color color);

    void setFillColor(Color fillColor);

    void setFontColor(Color fontColor);

    void setPattern(Pattern pattern);

    void setFillPattern(Pattern fillPattern);

    void setFontPattern(Pattern fontPattern);

    void setFont(Font font);

}
