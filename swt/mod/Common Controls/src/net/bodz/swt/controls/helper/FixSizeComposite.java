package net.bodz.swt.controls.helper;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class FixSizeComposite extends Composite {

    private Point fixed;

    public FixSizeComposite(Composite parent, int style) {
        super(parent, style);
    }

    public FixSizeComposite(Composite parent, int style, Point fixedSize) {
        super(parent, style);
        this.fixed = fixedSize;
    }

    @Override
    public Point computeSize(int hint, int hint2, boolean changed) {
        if (fixed != null)
            return fixed;
        return super.computeSize(hint, hint2, changed);
    }

    @Override
    public Point computeSize(int hint, int hint2) {
        if (fixed != null)
            return fixed;
        return super.computeSize(hint, hint2);
    }

    public void setFixedSize(Point size) {
        this.fixed = size;
    }

    public void setFixedSize(int width, int height) {
        setFixedSize(new Point(width, height));
    }

    public void unsetFixedSize() {
        this.fixed = null;
    }

}
