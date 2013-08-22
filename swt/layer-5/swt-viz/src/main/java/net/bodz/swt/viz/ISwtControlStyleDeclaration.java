package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Point;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;

public interface ISwtControlStyleDeclaration
        extends IGUIElementStyleDeclaration {

    @Override
    ISwtControlStyleDeclaration getParent();

    /**
     * @see #getWidth()
     * @see #getHeight()
     */
    Point getSize(Point dpi);

    // @Override
    // SwtColor getColor();

    // @Override
    // SwtColor getBackgroundColor();

}
