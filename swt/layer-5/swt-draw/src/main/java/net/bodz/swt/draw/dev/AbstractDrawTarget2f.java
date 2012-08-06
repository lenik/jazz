package net.bodz.swt.draw.dev;

import net.bodz.bas.geom_f.base.ILine2d;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.IPolygon2d;
import net.bodz.bas.geom_f.base.IRectangle2d;
import net.bodz.bas.geom_f.base.ITriangle2d;

public abstract class AbstractDrawTarget2f
        implements DrawTarget2f {

    // Kernel Implementation

    public abstract void drawPixel(float x, float y)
            throws DrawException;

    public abstract void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException;

    public void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        drawLine(x0, y0, x1, y1);
        drawLine(x1, y1, x2, y2);
        drawLine(x2, y2, x0, y0);
    }

    public void drawRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        drawLine(x0, y0, x1, y0);
        drawLine(x1, y0, x1, y1);
        drawLine(x1, y1, x0, y1);
        drawLine(x0, y1, x0, y0);
    }

    public void drawEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        IPolygon2d sampled = new IPolygon2d.SampleEllipse(x0, y0, x1, y1);
        drawPolygon(sampled);
    }

    public void drawPolygon(IPolygon2d polygon)
            throws DrawException {
        int n = polygon.getPointCount();
        switch (n) {
        case 1:
            drawPixel(polygon.getPointRef(0));
        case 0:
            return;
        }

        int offset = polygon.isOpened() ? 1 : 0;
        IPointRef2d pre = polygon.getPointRef((n + offset - 1) % n);
        for (int i = offset; i < n; i++) {
            IPointRef2d p = polygon.getPointRef(i);
            drawLine(pre, p);
            pre = p;
        }
    }

    public abstract void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException;

    public void fillRectangle(float x0, float y0, float x1, float y1)
            throws DrawException {
        fillTriangle(x0, y0, x1, y0, x1, y1);
        fillTriangle(x1, y0, x1, y1, x0, y1);
    }

    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        IPolygon2d sampled = new IPolygon2d.SampleEllipse(x0, y0, x1, y1);
        fillPolygon(sampled);
    }

    public void fillPolygon(IPolygon2d polygon)
            throws DrawException {
        ITriangle2d[] triangles = polygon.toTriangleArray();
        for (int i = 0; i < triangles.length; i++)
            fillTriangle(triangles[i]);
    }

    // Wrappers

    public void drawPixel(IPointRef2d point)
            throws DrawException {
        drawPixel(point.getX(), point.getY());
    }

    public void drawLine(IPointRef2d start, IPointRef2d end)
            throws DrawException {
        drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public void drawLine(ILine2d line)
            throws DrawException {
        drawLine(line.getX0(), line.getY0(), line.getX1(), line.getY1());
    }

    public void drawTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException {
        drawTriangle(p0.getX(), p0.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void drawTriangle(ITriangle2d triangle)
            throws DrawException {
        drawTriangle(triangle.getX0(), triangle.getY0(), triangle.getX1(), triangle.getY1(), triangle.getX2(), triangle.getY2());
    }

    public void drawRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        drawRectangle(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

    public void drawRectangle(IRectangle2d rect)
            throws DrawException {
        drawRectangle(rect.getX0(), rect.getY0(), rect.getX2(), rect.getY2());
    }

    public void drawEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        drawEllipse(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

    public void drawEllipse(IRectangle2d boundingBox)
            throws DrawException {
        drawEllipse(boundingBox.getX0(), boundingBox.getY0(), boundingBox.getX2(), boundingBox.getY2());
    }

    public void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawEllipse(centerX - radiusX, centerY - radiusY, centerX + radiusX, centerY + radiusY);
    }

    public void drawCircle(float centerX, float centerY, float radius)
            throws DrawException {
        drawEllipse(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    public void drawCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(center.getX(), center.getY(), radiusX, radiusY);
    }

    public void drawCircle(IPointRef2d center, float radius)
            throws DrawException {
        drawCircle(center.getX(), center.getY(), radius, radius);
    }

    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPolygon2d polygon = new IPolygon2d.StaticFixed(x, y, offset, count);
        drawPolygon(polygon);
    }

    public void drawPolygon(float[] x, float[] y, int size)
            throws DrawException {
        drawPolygon(x, y, size, 0);
    }

    public void drawPolygon(float[] x, float[] y)
            throws DrawException {
        assert x != null;
        assert y != null;
        int size = Math.min(x.length, y.length);
        drawPolygon(x, y, size, 0);
    }

    public void drawPolygon(IPointRef2d[] points)
            throws DrawException {
        assert points != null;
        // Polygon2f polygon = new Polygon2f.FastInitArray(points,
        // Polygon2f.FastInitArray.REFERENCE);
        // drawPolygon(polygon);
    }

    public void fillTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException {
        fillTriangle(p0.getX(), p0.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void fillTriangle(ITriangle2d triangle)
            throws DrawException {
        fillTriangle(triangle.getX0(), triangle.getY0(), triangle.getX1(), triangle.getY1(), triangle.getX2(), triangle.getY2());
    }

    public void fillRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        fillRectangle(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

    public void fillRectangle(IRectangle2d boundingBox)
            throws DrawException {
        fillRectangle(boundingBox.getX0(), boundingBox.getY0(), boundingBox.getX2(), boundingBox.getY2());
    }

    public void fillEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        fillEllipse(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

    public void fillEllipse(IRectangle2d boundingBox)
            throws DrawException {
        fillEllipse(boundingBox.getX0(), boundingBox.getY0(), boundingBox.getX2(), boundingBox.getY2());
    }

    public void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillEllipse(centerX - radiusX, centerY - radiusY, centerX + radiusX, centerY + radiusY);
    }

    public void fillCircle(float centerX, float centerY, float radius)
            throws DrawException {
        fillEllipse(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    public void fillCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(center.getX(), center.getY(), radiusX, radiusY);
    }

    public void fillCircle(IPointRef2d center, float radius)
            throws DrawException {
        fillCircle(center.getX(), center.getY(), radius, radius);
    }

    public void fillPolygon(IPointRef2d[] points)
            throws DrawException {
        assert points != null;
        // Polygon2f polygon = new Polygon2f.FastInitArray(points,
        // Polygon2f.FastInitArray.REFERENCE);
        // fillPolygon(polygon);
    }

    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPolygon2d polygon = new IPolygon2d.StaticFixed(x, y, offset, count);
        fillPolygon(polygon);
    }

    public void fillPolygon(float[] x, float[] y)
            throws DrawException {
        assert x != null;
        assert y != null;
        int size = Math.min(x.length, y.length);
        fillPolygon(x, y, size, 0);
    }

    // Styles

    private static Color dummyColor = new Color();
    private static Color dummyFillColor = new Color();
    private static Color dummyFontColor = new Color();
    private static Pattern dummyPattern = new Pattern();
    private static Pattern dummyFillPattern = new Pattern();
    private static Pattern dummyFontPattern = new Pattern();
    private static Stroke dummyStroke = new Stroke();
    private static Font dummyFont = new Font();

    public Stroke getStroke() {
        return dummyStroke;
    }

    public Color getColor() {
        return dummyColor;
    }

    public Color getFillColor() {
        return dummyFillColor;
    }

    public Color getFontColor() {
        return dummyFontColor;
    }

    public Pattern getPattern() {
        return dummyPattern;
    }

    public Pattern getFillPattern() {
        return dummyFillPattern;
    }

    public Pattern getFontPattern() {
        return dummyFontPattern;
    }

    public Font getFont() {
        return dummyFont;
    }

    public void setStroke(Stroke stroke) {
        // Do nothing.
    }

    public void setColor(Color color) {
        // Do nothing.
    }

    public void setFillColor(Color fillColor) {
        // Do nothing.
    }

    public void setFontColor(Color fontColor) {
        // Do nothing.
    }

    public void setPattern(Pattern pattern) {
        // Do nothing.
    }

    public void setFillPattern(Pattern fillPattern) {
        // Do nothing.
    }

    public void setFontPattern(Pattern fontPattern) {
        // Do nothing.
    }

    public void setFont(Font font) {
        // Do nothing.
    }

}
