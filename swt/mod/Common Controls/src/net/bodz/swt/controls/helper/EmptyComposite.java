package net.bodz.swt.controls.helper;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class EmptyComposite extends FixSizeComposite {

    static final Point empty = new Point(0, 0);

    public EmptyComposite(Composite parent, int style) {
        super(parent, style, empty);
    }

}
