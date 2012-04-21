package net.bodz.geom.util.gd;

import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.drawtarget.TransformedDrawTarget2f;
import net.bodz.geom.drawtarget.swt.SWTDrawTarget2f;
import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.transform.ViewTransformer2f;

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
    public Shape2f selshape;

    // Edit
    public EditablePointSet2f seledit;
    public int selpoint;
    public int selspoint;

    // Draw
    public Shape2f mkshape;

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

    public Point vtTarget(Point2f point) {
        Point2f swt = vt.transformTo(point);
        return new Point((int) swt.x(), (int) swt.y());
    }

    public Point2f vtSource(int x, int y) {
        Point2f point = new Point2f.Static(x, y);
        vt.transform(point);
        return point;
    }

}
