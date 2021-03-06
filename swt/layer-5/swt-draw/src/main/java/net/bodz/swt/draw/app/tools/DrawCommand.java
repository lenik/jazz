package net.bodz.swt.draw.app.tools;

import net.bodz.swt.c.canvas.Canvas;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.draw.app.SubCanvasMode;

public abstract class DrawCommand
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    public DrawCommand(Canvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

}
