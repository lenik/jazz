package net.bodz.swt.controls.gs;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;

public class SWTGRegionTest {

    public static void main(String[] args) {
        final SWTGRegion gr = new SWTGRegion();
        gr.add(new Rectangle(100, 100, 80, 80));
        gr.add(new Rectangle(200, 200, 100, 100));
        gr.subtract(new Rectangle(150, 150, 70, 80));

        ControlTestApp test = new ControlTestApp();
        Canvas canvas = new Canvas(test.parent, SWT.BORDER);
        canvas.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.fillRectangle(0, 0, e.width, e.height);
                Color color = new Color(e.display, 255, 0, 0);
                e.gc.setBackground(color);
                Region r = gr.toRegion();
                e.gc.setClipping(r);
                Rectangle bounds = r.getBounds();
                e.gc.fillRectangle(bounds);
                e.gc.setClipping((Region) null);
            }
        });
        test.run();
    }

}
