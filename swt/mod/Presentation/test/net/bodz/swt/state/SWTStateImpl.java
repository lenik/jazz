package net.bodz.swt.state;

import net.bodz.bas.types.sg.StateImpl;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class SWTStateImpl extends StateImpl implements SWTState {

    private static final long serialVersionUID = -5164514899871558948L;

    public SWTStateImpl(SWTStateGraph graph) {
        super(graph);
    }

    @Override
    public SWTStateGraph getGraph() {
        return (SWTStateGraph) graph;
    }

    public SWTState onKeyPressed(KeyEvent e) {
        return this;
    }

    public SWTState onKeyReleased(KeyEvent e) {
        return this;
    }

    public SWTState onMouseDoubleClick(MouseEvent e) {
        return this;
    }

    public SWTState onMouseDown(MouseEvent e) {
        return this;
    }

    public SWTState onMouseMove(MouseEvent e, MouseEvent d) {
        return this;
    }

    public SWTState onMouseUp(MouseEvent e, MouseEvent d) {
        return this;
    }

}
