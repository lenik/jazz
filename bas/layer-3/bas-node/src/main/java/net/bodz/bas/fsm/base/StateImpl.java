package net.bodz.bas.fsm.base;

import java.util.Collections;
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

    static final Map<Object, IState> STATIC_MAP = new HashMap<Object, IState>();
    static final Map<Object, IState> DUMMY_MAP = Collections.emptyMap();

    private IStateGraph graph;
    private Map<Object, IState> staticMap = DUMMY_MAP;

    public StateImpl(IStateGraph graph) {
        this.graph = graph;
    }

    public String getName() {
        return ObjectInfo.getSimpleId(this);
    }

    public int getType() {
        return NORMAL;
    }

//    @Override
//    public IStateGraph getGraph() {
//        return graph;
//    }

    @Override
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
