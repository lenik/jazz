package net.bodz.swt.draw.geom_i.particle;

import java.util.EventObject;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class Particle2dPaintEvent
        extends EventObject {

    private static final long serialVersionUID = -8403157009512252619L;

    public Particle2dCanvas canvas;

    /**
     * geometry index
     */
    public int index;

    public GC gc;

    public Rectangle viewRect;

    public Particle2dPaintEvent(Particle2dCanvas canvas, GC gc, int index, Rectangle viewRect) {
        super(canvas);
        this.canvas = canvas;
        this.gc = gc;
        this.index = index;
        this.viewRect = viewRect;
    }

    @Override
    public PaintEvent getSource() {
        return (PaintEvent) super.getSource();
    }

    @Override
    public String toString() {
        PaintEvent source = getSource();
        String s = source.toString();
        IParticleBounds2d gspace = canvas.getParticleBounds();
        s = s.substring(0, s.length() - 1) // remove trailing '}'
                + " space=" + gspace //
                + " index=" + index //
                + " vrect=" + viewRect //
        ;
        return s;
    }

}
