package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Point;

import net.bodz.bas.gui.style.StaticGUIStyleDeclaration;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class StrictSwtVizStyleDeclaration
        extends StaticGUIStyleDeclaration
        implements ISwtControlStyleDeclaration {

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return (ISwtControlStyleDeclaration) super.getParent();
    }

    @Override
    public Point getSize(Point ppi) {
        LengthMeasure width = getWidth();
        LengthMeasure height = getHeight();
        if (width == null && height == null)
            return null;

        int _width = 0;
        int _height = 0;
        if (width != null)
            _width = (int) width.pixels(ppi.x);
        if (height != null)
            _height = (int) height.pixels(ppi.y);

        return new Point(_width, _height);
    }

}
