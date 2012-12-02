package net.bodz.swt.graphics;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import net.bodz.bas.gui.spec1_f.IDrawContext2d;
import net.bodz.swt.gui.spec1.SwtDrawContext2d;

public abstract class ImagePainter {

    Image image;
    GC gc;

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

    public IDrawContext2d getDrawContext() {
        IDrawContext2d ctx = new SwtDrawContext2d(gc);
        return ctx;
    }

    public abstract void paint(GC gc);

}
