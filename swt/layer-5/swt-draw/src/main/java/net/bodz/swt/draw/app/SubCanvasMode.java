package net.bodz.swt.draw.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

public class SubCanvasMode
        extends DecoratedCanvasMode {

    private static final long serialVersionUID = 1L;

    private IClientCanvas canvas;

    public SubCanvasMode(ICanvasMode previous) {
        super(previous);
    }

    public SubCanvasMode(IClientCanvas canvas, ICanvasMode parent) {
        super(parent);
        this.canvas = canvas;
    }

    public IClientCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.keyCode == SWT.ESC)
            canvas.setMode(getWrapped());
    }

}
