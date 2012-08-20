package net.bodz.swt.draw.app;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

import net.bodz.swt.gui.state.SWTStateImpl;

public class DesignerState
        extends SWTStateImpl {

    public DesignerState(DesignerStateGraph graph) {
        super(graph);
        assert graph != null;
    }

    @Override
    public DesignerStateGraph getGraph() {
        return getGraph();
    }

    protected GDContext getContext() {
        return getGraph().context();
    }

    @Override
    public DesignerState onKeyPressed(KeyEvent e) {
        return this;
    }

    @Override
    public DesignerState onKeyReleased(KeyEvent e) {
        return this;
    }

    @Override
    public DesignerState onMouseDoubleClick(MouseEvent e) {
        return this;
    }

    @Override
    public DesignerState onMouseDown(MouseEvent e) {
        return this;
    }

    @Override
    public DesignerState onMouseMove(MouseEvent e, MouseEvent d) {
        return this;
    }

    @Override
    public DesignerState onMouseUp(MouseEvent e, MouseEvent d) {
        return this;
    }

    private static final long serialVersionUID = -2990862886372242470L;

}
