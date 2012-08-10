package net.bodz.bas.gui.dev;

import net.bodz.bas.geom_f.base.Line2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.Polygon2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.geom_f.base.Triangle2d;

public abstract class DecoratedDrawContext2d
        extends DecoratedTransformedView2d
        implements IDrawContext2d {

    private static final long serialVersionUID = 1L;

    public DecoratedDrawContext2d(IDrawContext2d _orig) {
        super(_orig);
    }

    @Override
    public IDrawContext2d getWrapped() {
        return (IDrawContext2d) super.getWrapped();
    }

    @Override
    public IColor getColor() {
        return getWrapped().getColor();
    }

    @Override
    public void setColor(IColor color) {
        getWrapped().setColor(color);
    }

    @Override
    public IColor getFillColor() {
        return getWrapped().getFillColor();
    }

    @Override
    public void setFillColor(IColor fillColor) {
        getWrapped().setFillColor(fillColor);
    }

    @Override
    public IStroke getStroke() {
        return getWrapped().getStroke();
    }

    @Override
    public void setStroke(IStroke stroke) {
        getWrapped().setStroke(stroke);
    }

    @Override
    public IFillPattern getPattern() {
        return getWrapped().getPattern();
    }

    @Override
    public void setPattern(IFillPattern pattern) {
        getWrapped().setPattern(pattern);
    }

    @Override
    public IFillPattern getFillPattern() {
        return getWrapped().getFillPattern();
    }

    @Override
    public void setFillPattern(IFillPattern fillPattern) {
        getWrapped().setFillPattern(fillPattern);
    }

    @Override
    public IFont getFont() {
        return getWrapped().getFont();
    }

    @Override
    public void setFont(IFont font) {
        getWrapped().setFont(font);
    }

    @Override
    public void drawPixel(int x, int y)
            throws GraphicsOperationException {
        getWrapped().drawPixel(x, y);
    }

    @Override
    public void drawPixel(float x, float y)
            throws GraphicsOperationException {
        getWrapped().drawPixel(x, y);
    }

    @Override
    public void drawPixel(Point2d point)
            throws GraphicsOperationException {
        getWrapped().drawPixel(point);
    }

    @Override
    public void drawLine(Point2d start, Point2d end)
            throws GraphicsOperationException {
        getWrapped().drawLine(start, end);
    }

    @Override
    public void drawLine(Line2d line)
            throws GraphicsOperationException {
        getWrapped().drawLine(line);
    }

    @Override
    public void drawRectangle(Rectangle2d rect)
            throws GraphicsOperationException {
        getWrapped().drawRectangle(rect);
    }

    @Override
    public void drawTriangle(Triangle2d triangle)
            throws GraphicsOperationException {
        getWrapped().drawTriangle(triangle);
    }

    @Override
    public void drawCircle(Point2d center, float radius)
            throws GraphicsOperationException {
        getWrapped().drawCircle(center, radius);
    }

    @Override
    public void drawEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException {
        getWrapped().drawEllipse(boundingBox);
    }

    @Override
    public void drawEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException {
        getWrapped().drawEllipse(center, radiusX, radiusY);
    }

    @Override
    public void drawPolygon(Polygon2d polygon)
            throws GraphicsOperationException {
        getWrapped().drawPolygon(polygon);
    }

    @Override
    public void fillRectangle(Rectangle2d rect)
            throws GraphicsOperationException {
        getWrapped().fillRectangle(rect);
    }

    @Override
    public void fillTriangle(Triangle2d triangle)
            throws GraphicsOperationException {
        getWrapped().fillTriangle(triangle);
    }

    @Override
    public void fillCircle(Point2d center, float radius)
            throws GraphicsOperationException {
        getWrapped().fillCircle(center, radius);
    }

    @Override
    public void fillEllipse(Rectangle2d boundingBox)
            throws GraphicsOperationException {
        getWrapped().fillEllipse(boundingBox);
    }

    @Override
    public void fillEllipse(Point2d center, float radiusX, float radiusY)
            throws GraphicsOperationException {
        getWrapped().fillEllipse(center, radiusX, radiusY);
    }

    @Override
    public void fillPolygon(Polygon2d polygon)
            throws GraphicsOperationException {
        getWrapped().fillPolygon(polygon);
    }

}
