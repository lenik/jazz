package net.bodz.geom.util.gd;

import java.util.Map;

public class GDStateGraph
        extends SWTStateGraphImpl {

    private static final long serialVersionUID = 7221218362819533191L;

    public GDStateGraph() {
        super();
    }

    public GDStateGraph(SWTState start) {
        super(start);
    }

    public GDStateGraph(GDContext context) {
        super(context);
    }

    public GDStateGraph(SWTState start, GDContext context) {
        super(start, context);
    }

    public GDStateGraph(Map<Object, State> registry, Object startKey) {
        super(registry, startKey);
    }

    public GDStateGraph(Map<Object, State> registry, Object startKey, GDContext context) {
        super(registry, startKey, context);
    }

    // -o StateGraph

    @Override
    public GDContext context() {
        return (GDContext) super.context();
    }

    @Override
    public GDState current() {
        return (GDState) super.current();
    }

    @Override
    public GDState get(Object key) {
        return (GDState) super.get(key);
    }

    @Override
    public GDState pop() {
        return (GDState) super.pop();
    }

    @Override
    public GDState recv(Object message) {
        return (GDState) super.recv(message);
    }

}
