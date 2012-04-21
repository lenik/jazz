package net.bodz.geom.util.gd.states;

import net.bodz.geom.util.gd.GDState;
import net.bodz.geom.util.gd.GDStateGraph;

import org.eclipse.swt.events.MouseEvent;

public class EditMajor
        extends GDState {

    public EditMajor(GDStateGraph graph) {
        super(graph);
    }

    @Override
    public GDState onMouseDown(MouseEvent e) {
        return super.onMouseDown(e);
    }

    @Override
    public GDState onMouseMove(MouseEvent e, MouseEvent d) {
        return super.onMouseMove(e, d);
    }

    @Override
    public GDState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public GDState onMouseDoubleClick(MouseEvent e) {
        return super.onMouseDoubleClick(e);
    }

    private static final long serialVersionUID = -5929768636672028188L;

}
