package net.bodz.swt.ui.style;

import org.eclipse.swt.SWT;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.unit.std.ILength;
import net.bodz.bas.i18n.unit.std.Length;
import net.bodz.bas.ui.style.IStrokeType;
import net.bodz.bas.ui.style.property.LineCapMode;
import net.bodz.bas.ui.style.property.LineJoinMode;

public abstract class AbstractSwtStrokeType
        implements ISwtStrokePattern, IStrokeType {

    private static final long serialVersionUID = 1L;

    /** ⇱ Implementaton Of {@link IStrokeType}. */
    /* _____________________________ */static section.iface __GUI__;

    @Override
    public ILength getLineWidth() {
        int lineWidth = getSwtLineWidth();
        return Length.PIXEL(lineWidth);
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
