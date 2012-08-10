package net.bodz.swt.draw.core.particle;

import net.bodz.swt.c.test.ControlTestApp;
import net.bodz.swt.draw.core.particle.GridParticleBounds;
import net.bodz.swt.draw.core.particle.ParticleCanvas;
import net.bodz.swt.draw.core.particle.GridParticleBounds.HFirst;

import org.eclipse.swt.SWT;

public class ParticleCanvasTest {

    static final int LARGE = 0;
    static final int EFFICIENCY = 1;

    static int testMode = LARGE;

    public static void main(String[] args) {
        ControlTestApp test = new ControlTestApp();
        HFirst space;
        if (testMode == EFFICIENCY) {
            space = new GridParticleBounds.HFirst(10000, 100);
        } else {
            space = new GridParticleBounds.HFirst(10, 3);
            space.setCellSize(90);
            space.setPadding(10);
        }
        ParticleCanvas canvas = new ParticleCanvas(test.holder, SWT.BORDER, space);
        System.out.println("Created: " + canvas);
        test.run();
    }

}
