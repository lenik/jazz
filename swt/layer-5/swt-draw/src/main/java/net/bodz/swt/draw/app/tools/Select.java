package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;

import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.swt.draw.app.DesignerState;
import net.bodz.swt.draw.app.DesignerStateGraph;
import net.bodz.swt.draw.app.GDContext;

public class Select
        extends DesignerState {

    static int[] DASH_SELECT = new int[] { 1, 1, };

    public Select(DesignerStateGraph graph) {
        super(graph);
    }

    @Override
    public DesignerState onMouseDown(MouseEvent e) {

        return super.onMouseDown(e);
    }

    @Override
    public DesignerState onMouseMove(MouseEvent e, MouseEvent d) {
        GDContext context = getContext();

        if (d != null) {
            // Drag a select-range-box
            GC gc = new GC(context.preview);
            gc.setLineDash(DASH_SELECT);
            gc.drawRectangle(d.x, d.y, e.x - d.x + 1, e.y - d.y + 1);
            gc.dispose();

        } else {
            Point2d p = context.vtSource(e.x, e.y);

            /* is p on some object ? */
            IShape2d pick = context.shapes.pick(p);
            if (pick == null)
                return this;

            // TODO - Point2f dp = context.vtSource(d.x, d.y);
            // GC gc = new GC(context.preview);
        }
        return super.onMouseMove(e, d);
    }

    @Override
    public DesignerState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public DesignerState onMouseDoubleClick(MouseEvent e) {
        GDContext context = getContext();

        Point2d p = context.vtSource(e.x, e.y);

        /* is p on some object ? */
        IShape2d pick = context.shapes.pick(p);
        if (pick == null)
            return this;

        return super.onMouseDoubleClick(e);
    }

    private static final long serialVersionUID = -7370181553009703148L;

}
