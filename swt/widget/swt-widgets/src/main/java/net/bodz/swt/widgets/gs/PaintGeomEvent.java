package net.bodz.swt.widgets.gs;

import java.util.EventObject;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class PaintGeomEvent
        extends EventObject {

    private static final long serialVersionUID = -8403157009512252619L;

    public GeomCanvas canvas;

    /**
     * geometry index
     */
    public int index;

    public GC gc;

    public Rectangle viewRect;

    public PaintGeomEvent(GeomCanvas canvas, GC gc, int index, Rectangle viewRect) {
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
        GeomSpace gspace = canvas.getGeomSpace();
        s = s.substring(0, s.length() - 1) // remove trailing '}'
                + " space=" + gspace // //$NON-NLS-1$
                + " index=" + index // //$NON-NLS-1$
                + " vrect=" + viewRect // //$NON-NLS-1$
        ;
        return s;
    }

}
