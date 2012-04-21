package net.bodz.swt.widgets.util;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class EmptyComposite
        extends FixSizeComposite {

    static final Point empty = new Point(1, 1);

    public EmptyComposite(Composite parent, int style) {
        super(parent, style, empty);
    }

}
