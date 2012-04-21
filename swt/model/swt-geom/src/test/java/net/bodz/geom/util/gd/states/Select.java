package net.bodz.geom.util.gd.states;

import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.util.gd.GDContext;
import net.bodz.geom.util.gd.GDState;
import net.bodz.geom.util.gd.GDStateGraph;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;

public class Select
        extends GDState {

    static int[] DASH_SELECT = new int[] { 1, 1, };

    public Select(GDStateGraph graph) {
        super(graph);
    }

    @Override
    public GDState onMouseDown(MouseEvent e) {

        return super.onMouseDown(e);
    }

    @Override
    public GDState onMouseMove(MouseEvent e, MouseEvent d) {
        GDContext context = getContext();

        if (d != null) {
            // Drag a select-range-box
            GC gc = new GC(context.preview);
            gc.setLineDash(DASH_SELECT);
            gc.drawRectangle(d.x, d.y, e.x - d.x + 1, e.y - d.y + 1);
            gc.dispose();

        } else {
            Point2f p = context.vtSource(e.x, e.y);

            /* is p on some object ? */
            Shape2f pick = context.shapes.pick(p);
            if (pick == null)
                return this;

            // TODO - Point2f dp = context.vtSource(d.x, d.y);
            // GC gc = new GC(context.preview);
        }
        return super.onMouseMove(e, d);
    }

    @Override
    public GDState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public GDState onMouseDoubleClick(MouseEvent e) {
        GDContext context = getContext();

        Point2f p = context.vtSource(e.x, e.y);

        /* is p on some object ? */
        Shape2f pick = context.shapes.pick(p);
        if (pick == null)
            return this;

        return super.onMouseDoubleClick(e);
    }

    private static final long serialVersionUID = -7370181553009703148L;

}
