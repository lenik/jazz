package net.bodz.swt.draw.app;

import java.util.Map;

import net.bodz.bas.fsm.base.IState;
import net.bodz.swt.gui.state.ISWTState;
import net.bodz.swt.gui.state.SWTStateGraphImpl;

public class DesignerStateGraph
        extends SWTStateGraphImpl {

    private static final long serialVersionUID = 7221218362819533191L;

    public DesignerStateGraph() {
        super();
    }

    public DesignerStateGraph(ISWTState start) {
        super(start);
    }

    public DesignerStateGraph(GDContext context) {
        super(context);
    }

    public DesignerStateGraph(ISWTState start, GDContext context) {
        super(start, context);
    }

    public DesignerStateGraph(Map<Object, IState> registry, Object startKey) {
        super(registry, startKey);
    }

    public DesignerStateGraph(Map<Object, IState> registry, Object startKey, GDContext context) {
        super(registry, startKey, context);
    }

    // -o StateGraph

    @Override
    public GDContext context() {
        return (GDContext) super.context();
    }

    @Override
    public DesignerState current() {
        return (DesignerState) super.current();
    }

    @Override
    public DesignerState get(Object key) {
        return (DesignerState) super.get(key);
    }

    @Override
    public DesignerState pop() {
        return (DesignerState) super.pop();
    }

    @Override
    public DesignerState recv(Object message) {
        return (DesignerState) super.recv(message);
    }

}
