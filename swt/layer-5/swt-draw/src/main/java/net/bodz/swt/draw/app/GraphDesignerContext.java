package net.bodz.swt.draw.app;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.geom_f.api.IEditablePointSet2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.base.CompositeShape2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.tr.MatrixTransformer2d;
import net.bodz.bas.gui.dev.IDrawContext2d;
import net.bodz.swt.gui.dev.SWTDrawContext2d;
import net.bodz.swt.gui.state.SWTContext;

public class GraphDesignerContext
        extends SWTContext {

    public CompositeShape2d shapes;

    public Rectangle imageSize;

    public Image rendered;
    public Image preview;

    // Select
    public IShape2d selshape;

    // Edit
    public IEditablePointSet2d seledit;
    public int selpoint;
    public int selspoint;

    // Draw
    public IShape2d mkshape;

    // View
    public MatrixTransformer2d transformer;

    public IDrawContext2d getContext2d(GC gc, MatrixTransformer2d transformer) {
        SWTDrawContext2d ctx = new SWTDrawContext2d(gc);
        if (transformer != null)
            ctx.setTransformer(transformer);
        return ctx;
    }

    public IDrawContext2d getContext2d(GC gc) {
        return getContext2d(gc, transformer);
    }

    public void render() {
        GC gc = new GC(rendered);
        IDrawContext2d ctx = getContext2d(gc);
        shapes.draw(ctx);
    }

    public Point2d vtTarget(Point2d point) {
        Point2d target = point.shot();
        transformer.transform(target);
        return target;
    }

    public Point2d vtSource(int x, int y) {
        Point2d point = new Point2d(x, y);
        transformer.transform(point);
        return point;
    }

}
