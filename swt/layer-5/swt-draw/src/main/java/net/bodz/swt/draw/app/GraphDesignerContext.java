package net.bodz.swt.draw.app;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.geom.spec0_f.IEditablePointSet2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.tr.MatrixTransformer2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec2_f.Composite2d;
import net.bodz.bas.gui.dev.IDrawContext2d;
import net.bodz.swt.gui.dev.SWTDrawContext2d;
import net.bodz.swt.gui.state.SWTContext;

public class GraphDesignerContext
        extends SWTContext {

    public Composite2d shapes;

    public Rectangle imageSize;

    public Image rendered;
    public Image preview;

    // Select
    public IPrimitive2d selshape;

    // Edit
    public IEditablePointSet2d seledit;
    public int selpoint;
    public int selspoint;

    // Draw
    public IPrimitive2d mkshape;

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