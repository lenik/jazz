package net.bodz.swt.controls.gs;

import java.util.EventObject;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class PaintGeomEvent extends EventObject {

    private static final long serialVersionUID = -8403157009512252619L;

    /**
     * geom space
     */
    public GeomSpace          gspace;

    /**
     * geometry index
     */
    public int                index;

    public GC                 gc;

    /**
     * orig x (left of the geometry) in current view.
     */
    public int                x;

    /**
     * orig y (top of the geometry) in current view
     */
    public int                y;

    public PaintGeomEvent(PaintEvent e, GeomCanvas canvas, int index) {
        super(e);
        this.gspace = canvas.gspace;
        this.index = index;
        this.gc = e.gc;
        Rectangle bound = gspace.getBound(index);
        this.x = bound.x - canvas.xoff;
        this.y = bound.y - canvas.yoff;
    }

    @Override
    public PaintEvent getSource() {
        return (PaintEvent) super.getSource();
    }

    @Override
    public String toString() {
        PaintEvent source = getSource();
        String s = source.toString();
        s = s.substring(0, s.length() - 1) // remove trailing '}'
                + " space=" + gspace //
                + " index=" + index //
                + " x=" + x //
                + " y=" + y;
        return s;
    }

}
