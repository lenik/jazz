package net.bodz.swt.gui.a;

import org.eclipse.swt.graphics.Point;

import net.bodz.bas.ui.a.PreferredSize;

public class PreferredSizeAnnotation {

    public static Point parseSize(PreferredSize size) {
        if (size == null)
            return null;
        return new Point(size.width(), size.height());
    }

}
