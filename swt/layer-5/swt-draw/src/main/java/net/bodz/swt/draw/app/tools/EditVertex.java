package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.KeyEvent;

import net.bodz.bas.geom_f.api.IEditablePointSet2d;
import net.bodz.swt.c.canvas.Canvas;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.draw.app.SubCanvasMode;

public class EditVertex
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    // Shape2f shape;
    IEditablePointSet2d editable;
    int pointIndex;

    public EditVertex(Canvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

    boolean check(int index) {
        if (index < 0 || index >= editable.getPointCount())
            return false;
        return true;
    }

    @Override
    public void keyDown(KeyEvent e) {
        switch (e.keyCode) {
        case java.awt.event.KeyEvent.VK_DELETE:
        case java.awt.event.KeyEvent.VK_BACK_SPACE:
            if (!check(pointIndex))
                break;
            editable.removePoint(pointIndex);
            pointIndex %= editable.getPointCount();
            if (pointIndex == 0) {
                // the shape may be still exist, even no any point.
            }
            // refresh ..
            // notifyRefresh();
            break;
        }
    }

}
