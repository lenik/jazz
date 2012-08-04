package net.bodz.swt.draw.app;

import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IPointRef2d;
import net.bodz.geom.shape.base.StaticPoint2f;
import net.bodz.geom.transform.ViewTransformer2f;
import net.bodz.swt.draw.dev.DrawTarget2f;
import net.bodz.swt.draw.dev.TransformedDrawTarget2f;
import net.bodz.swt.draw.dev.swt.SWTDrawTarget2f;
import net.bodz.swt.gui.state.SWTContext;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GDContext
        extends SWTContext {

    public GDShapes2f shapes;

    public Rectangle imageSize;

    public Image rendered;
    public Image preview;

    // Select
    public IShape2f selshape;

    // Edit
    public EditablePointSet2f seledit;
    public int selpoint;
    public int selspoint;

    // Draw
    public IShape2f mkshape;

    // View
    public ViewTransformer2f vt;

    public DrawTarget2f getDT(GC gc, ViewTransformer2f transformer) {
        DrawTarget2f sdt = new SWTDrawTarget2f(gc);
        DrawTarget2f tdt = new TransformedDrawTarget2f(sdt, transformer);
        return tdt;
    }

    public DrawTarget2f getDT(GC gc) {
        return getDT(gc, vt);
    }

    public void render() {
        GC gc = new GC(rendered);
        DrawTarget2f dt = getDT(gc);
        shapes.draw(dt);
    }

    public Point vtTarget(IPointRef2d point) {
        IPointRef2d swt = vt.transformTo(point);
        return new Point((int) swt.x(), (int) swt.y());
    }

    public IPointRef2d vtSource(int x, int y) {
        IPointRef2d point = new StaticPoint2f(x, y);
        vt.transform(point);
        return point;
    }

}
