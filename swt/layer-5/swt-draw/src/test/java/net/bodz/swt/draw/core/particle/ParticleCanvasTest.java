package net.bodz.swt.draw.core.particle;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.draw.core.particle.GridParticleBounds.HFirst;

public class ParticleCanvasTest
        extends WidgetTester {

    static final int LARGE = 0;
    static final int EFFICIENCY = 1;

    static int testMode = LARGE;

    @Test
    public void test1() {
        HFirst space;
        if (testMode == EFFICIENCY) {
            space = new GridParticleBounds.HFirst(10000, 100);
        } else {
            space = new GridParticleBounds.HFirst(10, 3);
            space.setCellSize(90);
            space.setPadding(10);
        }
        ParticleCanvas canvas = new ParticleCanvas(body, SWT.BORDER, space);
        System.out.println("Created: " + canvas);
    }

}
