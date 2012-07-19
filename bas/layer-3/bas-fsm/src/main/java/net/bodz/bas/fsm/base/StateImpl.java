package net.bodz.bas.fsm.base;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.NotImplementedException;

/**
 * Implemented Features: - Composition
 */
public abstract class StateImpl
        implements IState {

    private static final long serialVersionUID = 1L;

    private static final Map<Object, IState> DummyMap = new HashMap<Object, IState>();

    protected IStateGraph graph;
    protected Map<Object, IState> staticMap = DummyMap;

    public StateImpl(IStateGraph graph) {
        this.graph = graph;
    }

    public String getName() {
        return ObjectInfo.getSimpleId(this);
    }

    public int getType() {
        return NORMAL;
    }

    public IStateGraph getGraph() {
        return graph;
    }

    public void setGraph(IStateGraph graph) {
        this.graph = graph;
    }

    public IState recv(Object message) {
        return staticMap.get(message);
    }

    /**
     * State scope entered-handler
     * 
     * @param prev
     *            The previous state
     */
    public void enter(IState prev) {
    }

    /**
     * State scope left-handler
     * 
     * @param next
     *            The next state
     */
    public void leave(IState next) {
    }

    public Map<Object, IState> getStaticMap() {
        return staticMap;
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }

}
