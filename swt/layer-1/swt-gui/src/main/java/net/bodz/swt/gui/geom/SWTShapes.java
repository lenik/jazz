package net.bodz.swt.gui.geom;

import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.geom_f.base.Rectangle2d;

public class SWTShapes {

    public static Rectangle convert(Rectangle2d o) {
        if (o == null)
            return null;
        else
            return new Rectangle( //
                    (int) o.getX0(), //
                    (int) o.getY0(), //
                    (int) o.getWidth(), //
                    (int) o.getHeight());
    }

    public static Rectangle2d convert(Rectangle o) {
        return Rectangle2d.sized(o.x, o.y, o.width, o.height);
    }

}
