package net.bodz.swt.gui.geom.region;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

public interface IRegion {

    void add(Rectangle rect);

    void subtract(Rectangle rect);

    Region toRegion();

    void dispose();

}
