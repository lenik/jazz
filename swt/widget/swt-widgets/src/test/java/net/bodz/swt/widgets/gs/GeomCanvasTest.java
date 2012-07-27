package net.bodz.swt.widgets.gs;

import net.bodz.swt.widgets.gs.GridSpace.HFirst;
import net.bodz.swt.widgets.test.ControlTestApp;

import org.eclipse.swt.SWT;

public class GeomCanvasTest {

    static final int LARGE = 0;
    static final int EFFICIENCY = 1;

    static int testMode = LARGE;

    public static void main(String[] args) {
        ControlTestApp test = new ControlTestApp();
        HFirst space;
        if (testMode == EFFICIENCY) {
            space = new GridSpace.HFirst(10000, 100);
        } else {
            space = new GridSpace.HFirst(10, 3);
            space.setCellSize(90);
            space.setPadding(10);
        }
        GeomCanvas canvas = new GeomCanvas(test.holder, SWT.BORDER, space);
        System.out.println("Created: " + canvas);
        test.run();
    }

}
