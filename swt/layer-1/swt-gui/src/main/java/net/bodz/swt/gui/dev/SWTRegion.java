package net.bodz.swt.gui.dev;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.gui.dev.AbstractRegion;
import net.bodz.swt.gui.geom.SWTShapes;

public class SWTRegion
        extends AbstractRegion {

    private final Region region;

    public SWTRegion() {
        region = new Region();
    }

    public SWTRegion(Device device) {
        region = new Region(device);
    }

    @Override
    public void add(Rectangle2d rectangle) {
        Rectangle rect = SWTShapes.convert(rectangle);
        ;
        region.add(rect);
    }

    @Override
    public void remove(Rectangle2d rectangle) {
        Rectangle rect = SWTShapes.convert(rectangle);
        region.subtract(rect);
    }

    @Override
    public void dispose() {
        region.dispose();
    }

    public Region getRegion() {
        return region;
    }

}