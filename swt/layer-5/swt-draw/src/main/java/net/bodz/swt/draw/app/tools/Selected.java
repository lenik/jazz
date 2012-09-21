package net.bodz.swt.draw.app.tools;

import net.bodz.swt.draw.app.ICanvasMode;
import net.bodz.swt.draw.app.IClientCanvas;
import net.bodz.swt.draw.app.SubCanvasMode;

public class Selected
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    public Selected(IClientCanvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

}
