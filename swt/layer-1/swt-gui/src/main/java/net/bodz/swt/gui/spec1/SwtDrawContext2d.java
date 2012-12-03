package net.bodz.swt.gui.spec1;

import org.eclipse.swt.graphics.GC;

import net.bodz.bas.geom.spec1_f.Line2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFillType;
import net.bodz.bas.gui.spec0.IFontType;
import net.bodz.bas.gui.spec0.IStrokeType;
import net.bodz.bas.gui.spec1_f.AbstractDrawContext2d;

public class SwtDrawContext2d
        extends AbstractDrawContext2d {

    private final GC gc;

    public SwtDrawContext2d(GC gc) {
        assert gc != null;
        this.gc = gc;
    }

    /**
     * Not tested: HOW TO DISPOSE ?
     */
    @Override
    public void finalize() {
        gc.dispose();
    }

    @Override
    public SwtColor getColor() {
        return new SwtColor(gc.getForeground());
    }

    @Override
    public void setColor(IColor color) {
        SwtColor sc = (SwtColor) color;
        gc.setForeground(sc.color);
    }

    @Override
    public SwtColor getFillColor() {
        return new SwtColor(gc.getBackground());
    }

    @Override
    public void setFillColor(IColor fillColor) {
        SwtColor sc = (SwtColor) fillColor;
        gc.setBackground(sc.color);
    }

    @Override
    public SwtStroke getStroke() {
        return new SwtStroke.Ref(gc);
    }

    @Override
    public void setStroke(IStrokeType _stroke) {
        SwtStroke stroke = (SwtStroke) _stroke;
        gc.setLineCap(stroke.getCap());
        gc.setLineDash(stroke.getDash());
        gc.setLineJoin(stroke.getJoin());
        gc.setLineStyle(stroke.getStyle());
        gc.setLineWidth(stroke.getWidth());
    }

    @Override
    public IFillType getPattern() {
        return new SwtFillPattern(gc.getForegroundPattern());
    }

    @Override
    public void setPattern(IFillType pattern) {
        SwtFillPattern sp = (SwtFillPattern) pattern;
        gc.setForegroundPattern(sp.pattern);
    }

    @Override
    public SwtFillPattern getFillPattern() {
        return new SwtFillPattern(gc.getBackgroundPattern());
    }

    @Override
    public void setFillPattern(IFillType fillPattern) {
        SwtFillPattern sp = (SwtFillPattern) fillPattern;
        gc.setBackgroundPattern(sp.pattern);
    }

    @Override
    public IFontType getFont() {
        return new SwtFontType(gc.getFont());
    }

    @Override
    public void setFont(IFontType font) {
        SwtFontType sf = (SwtFontType) font;
        gc.setFont(sf.font);
    }

    @Override
    public void drawPixel(int x, int y) {
        gc.drawPoint(x, y);
    }

    @Override
    public void drawPixel(float x, float y) {
        gc.drawPoint((int) x, (int) y);
    }

    @Override
    public void drawPixel(Point2d point) {
        gc.drawPoint((int) point.x, (int) point.y);
    }

    @Override
    public void drawLine(Point2d start, Point2d end) {
        gc.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

    @Override
    public void drawLine(Line2d line) {
        drawLine(line.point0, line.point1);
    }

    @Override
    public void drawRectangle(Rectangle2d rect) {
        Point2d p0 = rect.getPoint0();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        gc.drawRectangle((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void fillRectangle(Rectangle2d rect) {
        Point2d p0 = rect.getPoint0();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        gc.fillRectangle((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void drawTriangle(Triangle2d triangle) {
        // TODO triangle.toPolygon();
    }

    @Override
    public void fillTriangle(Triangle2d triangle) {
        // TODO triangle.toPolygon();
    }

    @Override
    public void drawPolygon(Polygon2d polygon) {
        int[] swt = convertPolygonToSWT(polygon);
        if (polygon.isClosed())
            gc.drawPolygon(swt);
        else
            gc.drawPolyline(swt);
    }

    @Override
    public void fillPolygon(Polygon2d polygon) {
        int[] swt = convertPolygonToSWT(polygon);
        gc.fillPolygon(swt);
    }

    @Override
    public void drawCircle(Point2d center, float radius) {
        float x0 = center.x - radius;
        float y0 = center.y - radius;
        int d = (int) (radius * 2);
        gc.drawOval((int) x0, (int) y0, d, d);
    }

    @Override
    public void fillCircle(Point2d center, float radius) {
        float x0 = center.x - radius;
        float y0 = center.y - radius;
        int d = (int) (radius * 2);
        gc.fillOval((int) x0, (int) y0, d, d);
    }

    @Override
    public void drawEllipse(Rectangle2d bbox) {
        Rectangle2d posi = bbox.shot();
        posi.positize();
        Point2d p0 = posi.getPoint0();
        int width = (int) posi.getWidth();
        int height = (int) posi.getHeight();
        gc.drawOval((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void fillEllipse(Rectangle2d bbox) {
        Rectangle2d posi = bbox.shot();
        posi.positize();
        Point2d p0 = posi.getPoint0();
        int width = (int) posi.getWidth();
        int height = (int) posi.getHeight();
        gc.fillOval((int) p0.x, (int) p0.y, width, height);
    }

    @Override
    public void drawEllipse(Point2d center, float radiusX, float radiusY) {
        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        int dx = (int) (radiusX * 2);
        int dy = (int) (radiusY * 2);
        gc.drawOval((int) x0, (int) y0, dx, dy);
    }

    @Override
    public void fillEllipse(Point2d center, float radiusX, float radiusY) {
        float x0 = center.x - radiusX;
        float y0 = center.y - radiusY;
        int dx = (int) (radiusX * 2);
        int dy = (int) (radiusY * 2);
        gc.fillOval((int) x0, (int) y0, dx, dy);
    }

    static int[] convertPolygonToSWT(Polygon2d polygon) {
        int n = polygon.getPointCount();
        int[] xy = new int[n + n];
        for (int i = 0; i < n; i++) {
            Point2d point = polygon.getPoint(i);
            xy[i + i] = (int) point.x;
            xy[i + i + 1] = (int) point.y;
        }
        return xy;
    }

}
