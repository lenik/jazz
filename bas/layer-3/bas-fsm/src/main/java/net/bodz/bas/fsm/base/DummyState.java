package net.bodz.bas.fsm.base;

public class DummyState extends StateImpl {

    private static final long serialVersionUID = 283780226489973729L;

    public DummyState() {
        super(null);
    }

    public DummyState(IStateGraph graph) {
        super(graph);
    }

}
