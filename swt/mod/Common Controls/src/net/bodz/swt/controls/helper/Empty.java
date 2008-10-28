package net.bodz.swt.controls.helper;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class Empty extends FixSizeComposite {

    static final Point empty = new Point(0, 0);

    public Empty(Composite parent, int style) {
        super(parent, style, empty);
    }

}
