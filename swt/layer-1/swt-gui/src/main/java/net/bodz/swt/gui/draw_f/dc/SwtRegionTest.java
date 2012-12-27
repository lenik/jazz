package net.bodz.swt.gui.draw_f.dc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.junit.Test;

import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.swt.c.test.WidgetTester;

public class SwtRegionTest
        extends WidgetTester {

    @Test
    public void test1() {
        final SwtRegion region = new SwtRegion();
        region.add(new Rectangle2d(100, 100, 80, 80));
        region.add(new Rectangle2d(200, 200, 100, 100));
        region.remove(new Rectangle2d(150, 150, 70, 80));

        Canvas canvas = new Canvas(body, SWT.BORDER);
        canvas.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.fillRectangle(0, 0, e.width, e.height);
                Color color = new Color(e.display, 255, 0, 0);
                e.gc.setBackground(color);
                Region r = region.getRegion();
                e.gc.setClipping(r);
                Rectangle bounds = r.getBounds();
                e.gc.fillRectangle(bounds);
                e.gc.setClipping((Region) null);
            }
        });
    }

}
