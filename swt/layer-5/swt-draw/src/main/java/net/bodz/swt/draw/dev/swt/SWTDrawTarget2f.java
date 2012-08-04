package net.bodz.swt.draw.dev.swt;

import net.bodz.geom.shape.base.IPointRef2d;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.swt.draw.dev.AbstractDrawTarget2f;
import net.bodz.swt.draw.dev.Color;
import net.bodz.swt.draw.dev.DrawException;
import net.bodz.swt.draw.dev.Font;
import net.bodz.swt.draw.dev.Pattern;
import net.bodz.swt.draw.dev.Stroke;

import org.eclipse.swt.graphics.GC;

public class SWTDrawTarget2f
        extends AbstractDrawTarget2f {

    private final GC gc;

    public SWTDrawTarget2f(GC gc) {
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
    public void drawPixel(float x, float y) {
        gc.drawPoint((int) x, (int) y);
    }

    @Override
    public void drawLine(float x0, float y0, float x1, float y1) {
        gc.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
    }

    @Override
    public void drawEllipse(float x0, float y0, float x1, float y1) {
        gc.drawOval((int) x0, (int) y0, (int) (x1 - x0), (int) (y1 - y0));
    }

    @Override
    public void drawPolygon(IPolygon2f polygon) {
        if (polygon.isClosed()) {
            int[] swt = convertPolygonToSWT(polygon);
            gc.drawPolygon(swt);
            return;
        }
        try {
            super.drawPolygon(polygon);
        } catch (DrawException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return;
    }

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2) {
        int[] xy = new int[6];
        xy[0] = (int) x0;
        xy[1] = (int) y0;
        xy[2] = (int) x1;
        xy[3] = (int) y1;
        xy[4] = (int) x2;
        xy[5] = (int) y2;
        gc.fillPolygon(xy);
    }

    @Override
    public void fillEllipse(float x0, float y0, float x1, float y1)
            throws DrawException {
        gc.fillOval((int) x0, (int) y0, (int) (x1 - x0), (int) (y1 - y0));
    }

    @Override
    public void fillPolygon(IPolygon2f polygon) {
        int[] swt = convertPolygonToSWT(polygon);
        gc.fillPolygon(swt);
    }

    public static int[] convertPolygonToSWT(IPolygon2f polygon) {
        int n = polygon.pointCount();
        int[] xy = new int[n + n];
        for (int i = 0; i < n; i++) {
            IPointRef2d point = polygon.pointRef(i);
            xy[i + i] = (int) point.x();
            xy[i + i + 1] = (int) point.y();
        }
        return xy;
    }

    // Styles

    @Override
    public SWTStroke getStroke() {
        return new SWTStroke.Ref(gc);
    }

    @Override
    public SWTColor getColor() {
        return new SWTColor(gc.getForeground());
    }

    @Override
    public SWTColor getFillColor() {
        return new SWTColor(gc.getBackground());
    }

    @Override
    public SWTColor getFontColor() {
        return new SWTColor(gc.getForeground());
    }

    @Override
    public SWTPattern getPattern() {
        return new SWTPattern(gc.getForegroundPattern());
    }

    @Override
    public SWTPattern getFillPattern() {
        return new SWTPattern(gc.getBackgroundPattern());
    }

    @Override
    public SWTPattern getFontPattern() {
        return new SWTPattern(gc.getForegroundPattern());
    }

    @Override
    public Font getFont() {
        return new SWTFont(gc.getFont());
    }

    @Override
    public void setStroke(Stroke stroke) {
        assert stroke instanceof SWTStroke;
        SWTStroke ss = (SWTStroke) stroke;
        gc.setLineCap(ss.getCap());
        gc.setLineDash(ss.getDash());
        gc.setLineJoin(ss.getJoin());
        gc.setLineStyle(ss.getStyle());
        gc.setLineWidth(ss.getWidth());
    }

    @Override
    public void setColor(Color color) {
        assert color instanceof SWTColor;
        SWTColor sc = (SWTColor) color;
        gc.setForeground(sc.color);
    }

    @Override
    public void setFillColor(Color fillColor) {
        assert fillColor instanceof SWTColor;
        SWTColor sc = (SWTColor) fillColor;
        gc.setBackground(sc.color);
    }

    @Override
    public void setFontColor(Color fontColor) {
        assert fontColor instanceof SWTColor;
        SWTColor sc = (SWTColor) fontColor;
        gc.setForeground(sc.color);
    }

    @Override
    public void setPattern(Pattern pattern) {
        assert pattern instanceof SWTPattern;
        SWTPattern sp = (SWTPattern) pattern;
        gc.setForegroundPattern(sp.pattern);
    }

    @Override
    public void setFillPattern(Pattern fillPattern) {
        assert fillPattern instanceof SWTPattern;
        SWTPattern sp = (SWTPattern) fillPattern;
        gc.setBackgroundPattern(sp.pattern);
    }

    @Override
    public void setFontPattern(Pattern fontPattern) {
        assert fontPattern instanceof SWTPattern;
        SWTPattern sp = (SWTPattern) fontPattern;
        gc.setForegroundPattern(sp.pattern);
    }

    @Override
    public void setFont(Font font) {
        assert font instanceof SWTFont;
        SWTFont sf = (SWTFont) font;
        gc.setFont(sf.font);
    }

}
