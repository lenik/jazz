package net.bodz.swt.graphics;

import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.drawtarget.swt.SWTDrawTarget2f;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public abstract class ImagePainter {

    Image image;
    GC    gc;

    public ImagePainter(Image image) {
        assert image != null;
        this.image = image;
    }

    private void gcBegin() {
        if (gc == null)
            gc = new GC(image);
    }

    private void gcEnd() {
        if (gc != null) {
            gc.dispose();
            gc = null;
        }
    }

    public void redraw(boolean end) {
        gcBegin();
        paint(gc);
        if (end)
            gcEnd();
    }

    public final void redraw() {
        redraw(true);
    }

    public void draw(boolean end) {
        gcBegin();
        if (gc == null)
            gc = new GC(image);
        paint(gc);
        if (end)
            gcEnd();
    }

    public void draw() {
        draw(true);
    }

    public DrawTarget2f getDrawTarget() {
        DrawTarget2f dt = new SWTDrawTarget2f(gc);
        return dt;
    }

    public abstract void paint(GC gc);
}
