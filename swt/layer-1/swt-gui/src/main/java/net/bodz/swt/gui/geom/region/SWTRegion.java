package net.bodz.swt.gui.geom.region;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

public class SWTRegion
        extends AbstractRegion {

    private final Region region;

    public SWTRegion() {
        region = new Region();
    }

    public SWTRegion(Device device) {
        region = new Region(device);
    }

    public void add(Rectangle rect) {
        region.add(rect);
    }

    public void subtract(Rectangle rect) {
        region.subtract(rect);
    }

    @Override
    public Region toRegion() {
        return region;
    }

    @Override
    public void dispose() {
        region.dispose();
    }

}
