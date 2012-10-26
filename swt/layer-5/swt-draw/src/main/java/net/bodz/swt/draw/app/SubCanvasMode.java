package net.bodz.swt.draw.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import net.bodz.swt.c.canvas.DecoratedCanvasMode;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.c.canvas.Canvas;

public class SubCanvasMode
        extends DecoratedCanvasMode {

    private static final long serialVersionUID = 1L;

    private Canvas canvas;

    public SubCanvasMode(ICanvasMode previous) {
        super(previous);
    }

    public SubCanvasMode(Canvas canvas, ICanvasMode parent) {
        super(parent);
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void keyUp(KeyEvent e) {
        if (e.keyCode == SWT.ESC)
            canvas.setMode(getWrapped());
    }

}
