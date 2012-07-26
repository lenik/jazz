package net.bodz.geom.util.gd;

import net.bodz.swt.state.SWTStateImpl;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class GDState
        extends SWTStateImpl {

    public GDState(GDStateGraph graph) {
        super(graph);
        assert graph != null;
    }

    @Override
    public GDStateGraph getGraph() {
        return getGraph();
    }

    protected GDContext getContext() {
        return getGraph().context();
    }

    @Override
    public GDState onKeyPressed(KeyEvent e) {
        return this;
    }

    @Override
    public GDState onKeyReleased(KeyEvent e) {
        return this;
    }

    @Override
    public GDState onMouseDoubleClick(MouseEvent e) {
        return this;
    }

    @Override
    public GDState onMouseDown(MouseEvent e) {
        return this;
    }

    @Override
    public GDState onMouseMove(MouseEvent e, MouseEvent d) {
        return this;
    }

    @Override
    public GDState onMouseUp(MouseEvent e, MouseEvent d) {
        return this;
    }

    private static final long serialVersionUID = -2990862886372242470L;

}
