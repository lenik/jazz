package net.bodz.swt.gui.style;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.style.IStrokeType;
import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

/**
 * WARNING: This class IS NOT serializable.
 */
public class GCStrokeType
        implements ISwtStrokeType, IStrokeType {

    private static final long serialVersionUID = 1L;

    private final GC gc;
    private final int xdpi;

    public GCStrokeType(GC gc) {
        if (gc == null)
            throw new NullPointerException("gc");
        this.gc = gc;

        Point dpi = gc.getDevice().getDPI();
        xdpi = dpi.x;
    }

    @Override
    public int getSwtLineCap() {
        return gc.getLineCap();
    }

    @Override
    public void setSwtLineCap(int cap) {
        gc.setLineCap(cap);
    }

    @Override
    public int[] getSwtLineDash() {
        return gc.getLineDash();
    }

    @Override
    public void setSwtLineDash(int[] dashes) {
        gc.setLineDash(dashes);
    }

    @Override
    public int getSwtLineJoin() {
        return gc.getLineJoin();
    }

    @Override
    public void setSwtLineJoin(int join) {
        gc.setLineJoin(join);
    }

    @Override
    public int getSwtLineStyle() {
        return gc.getLineStyle();
    }

    @Override
    public void setSwtLineStyle(int style) {
        gc.setLineStyle(style);
    }

    @Override
    public int getSwtLineWidth() {
        return gc.getLineWidth();
    }

    @Override
    public void setSwtLineWidth(int width) {
        gc.setLineWidth(width);
    }

    /** ⇱ Implementaton Of {@link IStrokeType}. */
    ;

    @Override
    public LengthMeasure getWidth() {
        return new LengthMeasure(gc.getLineWidth(), LengthMeasure.PIXEL);
    }

    @Override
    public void setWidth(LengthMeasure width) {
        gc.setLineWidth(width.pixels(xdpi));
    }

    @Override
    public LineCapMode getLineCap() {
        int lineCap = gc.getLineCap();
        switch (lineCap) {
        case SWT.CAP_FLAT:
            return LineCapMode.butt;
        case SWT.CAP_ROUND:
            return LineCapMode.round;
        case SWT.CAP_SQUARE:
            return LineCapMode.square;
        default:
            throw new UnexpectedException("Unexpected GC line cap mode: " + lineCap);
        }
    }

    @Override
    public void setLineCap(LineCapMode lineCap) {
        if (lineCap == null)
            throw new NullPointerException("lineCap");

        int swtLineCap = 0;

        switch (lineCap) {
        case butt:
            swtLineCap = SWT.CAP_FLAT;
            break;
        case round:
            swtLineCap = SWT.CAP_ROUND;
            break;
        case square:
            swtLineCap = SWT.CAP_SQUARE;
            break;
        }

        gc.setLineCap(swtLineCap);
    }

    @Override
    public LineJoinMode getLineJoin() {
        int lineJoin = gc.getLineJoin();
        switch (lineJoin) {
        case SWT.JOIN_BEVEL:
            return LineJoinMode.bevel;
        case SWT.JOIN_MITER:
            return LineJoinMode.miter;
        case SWT.JOIN_ROUND:
            return LineJoinMode.round;
        default:
            throw new UnexpectedException("Unexpected GC line join mode: " + lineJoin);
        }
    }

    @Override
    public void setLineJoin(LineJoinMode lineJoin) {
        if (lineJoin == null)
            throw new NullPointerException("lineJoin");

        int swtLineJoin = 0;

        switch (lineJoin) {
        case bevel:
            swtLineJoin = SWT.JOIN_BEVEL;
            break;
        case miter:
            swtLineJoin = SWT.JOIN_MITER;
            break;
        case round:
            swtLineJoin = SWT.JOIN_ROUND;
            break;
        }

        gc.setLineJoin(swtLineJoin);
    }

    @Override
    public int[] getDashPattern() {
        return gc.getLineDash();
    }

    @Override
    public void setDashPattern(int[] dashPattern) {
        gc.setLineDash(dashPattern);
    }

}
