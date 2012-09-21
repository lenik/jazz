package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;

import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.swt.draw.app.GDContext;
import net.bodz.swt.draw.app.ICanvasMode;
import net.bodz.swt.draw.app.IClientCanvas;
import net.bodz.swt.draw.app.SubCanvasMode;

public class Select
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    static int[] DASH_SELECT = new int[] { 1, 1, };

    public Select(IClientCanvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
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
            if (pick != null) {
                // TODO - Point2f dp = context.vtSource(d.x, d.y);
                // GC gc = new GC(context.preview);
            }
        }
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        GDContext context = getContext();

        Point2d p = context.vtSource(e.x, e.y);

        /* is p on some object ? */
        IShape2d pick = context.shapes.pick(p);
        if (pick != null) {
            super.mouseDoubleClick(e);
        }
    }

}
