package net.bodz.swt.state;

import net.bodz.bas.fsm.base.StateImpl;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class SWTStateImpl
        extends StateImpl
        implements ISWTState {

    private static final long serialVersionUID = -5164514899871558948L;

    public SWTStateImpl(ISWTStateGraph graph) {
        super(graph);
    }

    @Override
    public ISWTStateGraph getGraph() {
        return (ISWTStateGraph) graph;
    }

    public ISWTState onKeyPressed(KeyEvent e) {
        return this;
    }

    public ISWTState onKeyReleased(KeyEvent e) {
        return this;
    }

    public ISWTState onMouseDoubleClick(MouseEvent e) {
        return this;
    }

    public ISWTState onMouseDown(MouseEvent e) {
        return this;
    }

    public ISWTState onMouseMove(MouseEvent e, MouseEvent d) {
        return this;
    }

    public ISWTState onMouseUp(MouseEvent e, MouseEvent d) {
        return this;
    }

}
