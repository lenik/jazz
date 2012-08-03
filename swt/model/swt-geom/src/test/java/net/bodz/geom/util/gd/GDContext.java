package net.bodz.geom.util.gd;

import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.drawtarget.TransformedDrawTarget2f;
import net.bodz.geom.drawtarget.swt.SWTDrawTarget2f;
import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.StaticPoint2f;
import net.bodz.geom.transform.ViewTransformer2f;
import net.bodz.swt.state.SWTContext;

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

    public Point vtTarget(IPoint2f point) {
        IPoint2f swt = vt.transformTo(point);
        return new Point((int) swt.x(), (int) swt.y());
    }

    public IPoint2f vtSource(int x, int y) {
        IPoint2f point = new StaticPoint2f(x, y);
        vt.transform(point);
        return point;
    }

}
