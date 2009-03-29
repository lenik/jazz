package net.bodz.swt.controls.gs;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

/**
 * @TestBy GRegion
 */
public class SWTGRegion extends _GRegion {

    private final Region region;

    public SWTGRegion() {
        region = new Region();
    }

    public SWTGRegion(Device device) {
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
