package net.bodz.swt.draw.app.tools;

import net.bodz.swt.draw.app.ICanvasMode;
import net.bodz.swt.draw.app.IClientCanvas;

public class DrawPoint
        extends DrawCommand {

    private static final long serialVersionUID = 1L;

    public DrawPoint(IClientCanvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

}
