package net.bodz.swt.gui.style;

import org.eclipse.swt.SWT;

import net.bodz.bas.gui.style.IMutableStrokeType;
import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.Length;

public abstract class AbstractMutableSwtStrokeType
        extends AbstractSwtStrokeType
        implements IMutableStrokeType {

    private static final long serialVersionUID = 1L;

    private final int xppi;

    public AbstractMutableSwtStrokeType(int xppi) {
        this.xppi = xppi;
    }

    @Override
    public void setWidth(Length width) {
        int _width = width.pixels(xppi);
        setSwtLineWidth(_width);
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
        default:
            assert false;
        }

        setSwtLineCap(swtLineCap);
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

        default:
            assert false;
        }

        setSwtLineJoin(swtLineJoin);
    }

    @Override
    public void setDashPattern(int[] dashPattern) {
        setSwtLineDash(dashPattern);
    }

}
