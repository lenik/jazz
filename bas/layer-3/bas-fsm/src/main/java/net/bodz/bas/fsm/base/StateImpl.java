package net.bodz.bas.fsm.base;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.info.DisplayNameUtil;
import net.bodz.bas.util.exception.NotImplementedException;

/**
 * Implemented Features: - Composition
 */
public abstract class StateImpl
        implements State {

    private static final long serialVersionUID = 1L;

    private static final Map<Object, State> DummyMap = new HashMap<Object, State>();

    protected StateGraph graph;
    protected Map<Object, State> staticMap = DummyMap;

    public StateImpl(StateGraph graph) {
        this.graph = graph;
    }

    public String getName() {
        return DisplayNameUtil.getDisplayName(getClass());
    }

    public int getType() {
        return NORMAL;
    }

    public StateGraph getGraph() {
        return graph;
    }

    public void setGraph(StateGraph graph) {
        this.graph = graph;
    }

    public State recv(Object message) {
        return staticMap.get(message);
    }

    /**
     * State scope entered-handler
     * 
     * @param prev
     *            The previous state
     */
    public void enter(State prev) {
    }

    /**
     * State scope left-handler
     * 
     * @param next
     *            The next state
     */
    public void leave(State next) {
    }

    public Map<Object, State> getStaticMap() {
        return staticMap;
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }

}
