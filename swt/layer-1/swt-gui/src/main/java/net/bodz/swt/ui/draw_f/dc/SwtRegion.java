package net.bodz.swt.ui.draw_f.dc;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;

import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.gui.draw_f.dc.AbstractRegion2d;
import net.bodz.swt.ui.geom.SwtShapes;

public class SwtRegion
        extends AbstractRegion2d {

    private final Region region;

    public SwtRegion() {
        region = new Region();
    }

    public SwtRegion(Device device) {
        region = new Region(device);
    }

    public void add(SwtRegion region) {
        this.region.add(region.region);
    }

    public void remove(SwtRegion region) {
        this.region.subtract(region.region);
    }

    @Override
    public void add(Rectangle2d rectangle) {
        Rectangle rect = SwtShapes.convert(rectangle);
        region.add(rect);
    }

    @Override
    public void remove(Rectangle2d rectangle) {
        Rectangle rect = SwtShapes.convert(rectangle);
        region.subtract(rect);
    }

    @Override
    public void add(Polygon2d polygon) {
        int[] array = SwtShapes.toXYArray(polygon);
        region.add(array);
    }

    @Override
    public void remove(Polygon2d polygon) {
        int[] array = SwtShapes.toXYArray(polygon);
        region.subtract(array);
    }

    @Override
    public void dispose() {
        region.dispose();
    }

    public Region getRegion() {
        return region;
    }

}
