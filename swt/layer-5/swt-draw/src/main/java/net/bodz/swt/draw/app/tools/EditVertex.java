package net.bodz.swt.draw.app.tools;

import net.bodz.bas.fsm.base.IState;
import net.bodz.bas.geom_f.base.IEditablePointSet2d;
import net.bodz.swt.draw.app.GDState;
import net.bodz.swt.draw.app.GDStateGraph;

import org.eclipse.swt.events.KeyEvent;

public class EditVertex
        extends GDState {

    // Shape2f shape;
    IEditablePointSet2d editable;
    int pointIndex;

    public EditVertex(GDStateGraph graph) {
        super(graph);
    }

    @Override
    public void enter(IState prev) {
        super.enter(prev);
    }

    boolean check(int index) {
        if (index < 0 || index >= editable.getPointCount())
            return false;
        return true;
    }

    @Override
    public GDState onKeyPressed(KeyEvent e) {
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
        return super.onKeyPressed(e);
    }

    private static final long serialVersionUID = -2085947787836396117L;

}
