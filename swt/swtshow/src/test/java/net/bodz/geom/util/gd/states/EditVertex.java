package net.bodz.geom.util.gd.states;

import net.bodz.bas.types.sg.State;
import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.util.gd.GDState;
import net.bodz.geom.util.gd.GDStateGraph;

import org.eclipse.swt.events.KeyEvent;

public class EditVertex extends GDState {

    // Shape2f shape;
    EditablePointSet2f editable;
    int                pointIndex;

    public EditVertex(GDStateGraph graph) {
        super(graph);
    }

    @Override
    public void enter(State prev) {
        super.enter(prev);
    }

    boolean check(int index) {
        if (index < 0 || index >= editable.pointCount())
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
            pointIndex %= editable.pointCount();
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
