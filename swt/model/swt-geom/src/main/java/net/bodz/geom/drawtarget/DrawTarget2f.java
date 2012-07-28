package net.bodz.geom.drawtarget;

import net.bodz.geom.shape.base.ILine2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;

public interface DrawTarget2f {

    void drawPixel(float x, float y)
            throws DrawException;

    void drawPixel(IPoint2f point)
            throws DrawException;

    void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawLine(IPoint2f start, IPoint2f end)
            throws DrawException;

    void drawLine(ILine2f line)
            throws DrawException;

    void drawRectangle(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    void drawRectangle(IRectangle2f rect)
            throws DrawException;

    void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException;

    void drawTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException;

    void drawTriangle(ITriangle2f triangle)
            throws DrawException;

    void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException;

    void drawEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    void drawEllipse(IRectangle2f boundingBox)
            throws DrawException;

    // void drawEllipse(Ellipse2f ellipse);
    void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException;

    void drawCircle(float centerX, float centerY, float radius)
            throws DrawException;

    void drawCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException;

    void drawCircle(IPoint2f center, float radius)
            throws DrawException;

    void drawPolygon(IPolygon2f polygon)
            throws DrawException;

    void drawPolygon(IPoint2f[] points)
            throws DrawException;

    void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException;

    void drawPolygon(float[] x, float[] y)
            throws DrawException;

    void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException;

    void fillTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException;

    void fillTriangle(ITriangle2f triangle)
            throws DrawException;

    void fillRectangle(float x0, float y0, float x1, float y1)
            throws DrawException;

    void fillRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    void fillRectangle(IRectangle2f boundingBox)
            throws DrawException;

    void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException;

    void fillEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException;

    void fillEllipse(IRectangle2f boundingBox)
            throws DrawException;

    // void drawFilledEllipse(Ellipse2f ellipse);
    void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException;

    void fillCircle(float centerX, float centerY, float radius)
            throws DrawException;

    void fillCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException;

    void fillCircle(IPoint2f center, float radius)
            throws DrawException;

    void fillPolygon(IPolygon2f polygon)
            throws DrawException;

    void fillPolygon(IPoint2f[] points)
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
