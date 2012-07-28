package net.bodz.geom.drawtarget;

import net.bodz.geom.shape.base.ILine2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;

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
        IPolygon2f sampled = new IPolygon2f.SampleEllipse(x0, y0, x1, y1);
        drawPolygon(sampled);
    }

    public void drawPolygon(IPolygon2f polygon)
            throws DrawException {
        int n = polygon.pointCount();
        switch (n) {
        case 1:
            drawPixel(polygon.pointRef(0));
        case 0:
            return;
        }

        int offset = polygon.isOpened() ? 1 : 0;
        IPoint2f pre = polygon.pointRef((n + offset - 1) % n);
        for (int i = offset; i < n; i++) {
            IPoint2f p = polygon.pointRef(i);
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
        IPolygon2f sampled = new IPolygon2f.SampleEllipse(x0, y0, x1, y1);
        fillPolygon(sampled);
    }

    public void fillPolygon(IPolygon2f polygon)
            throws DrawException {
        ITriangle2f[] triangles = polygon.toTriangles();
        for (int i = 0; i < triangles.length; i++)
            fillTriangle(triangles[i]);
    }

    // Wrappers

    public void drawPixel(IPoint2f point)
            throws DrawException {
        drawPixel(point.x(), point.y());
    }

    public void drawLine(IPoint2f start, IPoint2f end)
            throws DrawException {
        drawLine(start.x(), start.y(), end.x(), end.y());
    }

    public void drawLine(ILine2f line)
            throws DrawException {
        drawLine(line.x0(), line.y0(), line.x1(), line.y1());
    }

    public void drawTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException {
        drawTriangle(p0.x(), p0.y(), p1.x(), p1.y(), p2.x(), p2.y());
    }

    public void drawTriangle(ITriangle2f triangle)
            throws DrawException {
        drawTriangle(triangle.x0(), triangle.y0(), triangle.x1(), triangle.y1(), triangle.x2(), triangle.y2());
    }

    public void drawRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        drawRectangle(p0.x(), p0.y(), p1.x(), p1.y());
    }

    public void drawRectangle(IRectangle2f rect)
            throws DrawException {
        drawRectangle(rect.x0(), rect.y0(), rect.x2(), rect.y2());
    }

    public void drawEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        drawEllipse(p0.x(), p0.y(), p1.x(), p1.y());
    }

    public void drawEllipse(IRectangle2f boundingBox)
            throws DrawException {
        drawEllipse(boundingBox.x0(), boundingBox.y0(), boundingBox.x2(), boundingBox.y2());
    }

    public void drawCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        drawEllipse(centerX - radiusX, centerY - radiusY, centerX + radiusX, centerY + radiusY);
    }

    public void drawCircle(float centerX, float centerY, float radius)
            throws DrawException {
        drawEllipse(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    public void drawCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException {
        drawCircle(center.x(), center.y(), radiusX, radiusY);
    }

    public void drawCircle(IPoint2f center, float radius)
            throws DrawException {
        drawCircle(center.x(), center.y(), radius, radius);
    }

    public void drawPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPolygon2f polygon = new IPolygon2f.StaticFixed(x, y, offset, count);
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

    public void drawPolygon(IPoint2f[] points)
            throws DrawException {
        assert points != null;
        // Polygon2f polygon = new Polygon2f.FastInitArray(points,
        // Polygon2f.FastInitArray.REFERENCE);
        // drawPolygon(polygon);
    }

    public void fillTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException {
        fillTriangle(p0.x(), p0.y(), p1.x(), p1.y(), p2.x(), p2.y());
    }

    public void fillTriangle(ITriangle2f triangle)
            throws DrawException {
        fillTriangle(triangle.x0(), triangle.y0(), triangle.x1(), triangle.y1(), triangle.x2(), triangle.y2());
    }

    public void fillRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        fillRectangle(p0.x(), p0.y(), p1.x(), p1.y());
    }

    public void fillRectangle(IRectangle2f boundingBox)
            throws DrawException {
        fillRectangle(boundingBox.x0(), boundingBox.y0(), boundingBox.x2(), boundingBox.y2());
    }

    public void fillEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        fillEllipse(p0.x(), p0.y(), p1.x(), p1.y());
    }

    public void fillEllipse(IRectangle2f boundingBox)
            throws DrawException {
        fillEllipse(boundingBox.x0(), boundingBox.y0(), boundingBox.x2(), boundingBox.y2());
    }

    public void fillCircle(float centerX, float centerY, float radiusX, float radiusY)
            throws DrawException {
        fillEllipse(centerX - radiusX, centerY - radiusY, centerX + radiusX, centerY + radiusY);
    }

    public void fillCircle(float centerX, float centerY, float radius)
            throws DrawException {
        fillEllipse(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    public void fillCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException {
        fillCircle(center.x(), center.y(), radiusX, radiusY);
    }

    public void fillCircle(IPoint2f center, float radius)
            throws DrawException {
        fillCircle(center.x(), center.y(), radius, radius);
    }

    public void fillPolygon(IPoint2f[] points)
            throws DrawException {
        assert points != null;
        // Polygon2f polygon = new Polygon2f.FastInitArray(points,
        // Polygon2f.FastInitArray.REFERENCE);
        // fillPolygon(polygon);
    }

    public void fillPolygon(float[] x, float[] y, int offset, int count)
            throws DrawException {
        IPolygon2f polygon = new IPolygon2f.StaticFixed(x, y, offset, count);
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
