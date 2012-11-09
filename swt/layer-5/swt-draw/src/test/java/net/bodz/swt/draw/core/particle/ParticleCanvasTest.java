package net.bodz.swt.draw.core.particle;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.draw.geom_i.particle.GridParticleBounds2d;
import net.bodz.swt.draw.geom_i.particle.GridParticleBounds2d.HFirst;
import net.bodz.swt.draw.geom_i.particle.Particle2dCanvas;

public class ParticleCanvasTest
        extends WidgetTester {

    static final int LARGE = 0;
    static final int EFFICIENCY = 1;

    static int testMode = LARGE;

    @Test
    public void test1() {
        HFirst space;
        if (testMode == EFFICIENCY) {
            space = new GridParticleBounds2d.HFirst(10000, 100);
        } else {
            space = new GridParticleBounds2d.HFirst(10, 3);
            space.setCellSize(90);
            space.setPadding(10);
        }
        Particle2dCanvas canvas = new Particle2dCanvas(body, SWT.BORDER, space);
        System.out.println("Created: " + canvas);
    }

}
