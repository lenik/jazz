package net.bodz.swt.gui.style;

import org.eclipse.swt.SWT;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.style.IStrokeType;
import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public abstract class AbstractSwtStrokeType
        implements ISwtStrokePattern, IStrokeType {

    private static final long serialVersionUID = 1L;

    /** ⇱ Implementaton Of {@link IStrokeType}. */
    ;

    @Override
    public LengthMeasure getWidth() {
        int lineWidth = getSwtLineWidth();
        return LengthMeasure.PIXEL(lineWidth);
    }

    @Override
    public LineCapMode getLineCap() {
        int lineCap = getSwtLineCap();
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
    public LineJoinMode getLineJoin() {
        int lineJoin = getSwtLineJoin();
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
    public int[] getDashPattern() {
        return getSwtLineDash();
    }

}