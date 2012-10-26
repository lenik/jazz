package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.MouseEvent;

import net.bodz.swt.c.canvas.Canvas;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.draw.app.SubCanvasMode;

public class ViewPan
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    public ViewPan(Canvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
        // vt.translate(e.x - d.x, e.y - d.y);
        d.x = e.x;
        d.y = e.y;
    }

    // TODO Scroll/MouseWheel

}
