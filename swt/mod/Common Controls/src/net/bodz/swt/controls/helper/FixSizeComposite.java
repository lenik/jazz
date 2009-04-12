package net.bodz.swt.controls.helper;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * @test FixSizeCompositeTest
 */
public class FixSizeComposite extends Composite {

    private Point fixed;

    public FixSizeComposite(Composite parent, int style) {
        super(parent, style);
    }

    public FixSizeComposite(Composite parent, int style, Point size) {
        super(parent, style);
        setFixedSize(size);
    }

    public FixSizeComposite(Composite parent, int style, int width, int height) {
        this(parent, style);
        setFixedSize(width, height);
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        if (fixed != null)
            return new Point(fixed.x, fixed.y);
        return super.computeSize(wHint, hHint, changed);
    }

    @Override
    public Point computeSize(int wHint, int hHint) {
        if (fixed != null)
            return new Point(fixed.x, fixed.y);
        return super.computeSize(wHint, hHint);
    }

    public void setFixedSize(Point size) {
        this.fixed = new Point(size.x, size.y);
    }

    public void setFixedSize(int width, int height) {
        this.fixed = new Point(width, height);
    }

    public void unsetFixedSize() {
        this.fixed = null;
    }

}
