package net.bodz.swt.controls.gs;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

public interface GRegion {

    void add(Rectangle rect);

    void subtract(Rectangle rect);

    Region toRegion();

    void dispose();

}
