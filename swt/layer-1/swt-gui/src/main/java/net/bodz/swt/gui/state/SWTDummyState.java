package net.bodz.swt.gui.state;

public class SWTDummyState
        extends DecoratedInputBehavior {

    private static final long serialVersionUID = 2492497528377735042L;

    public SWTDummyState() {
        super(null);
    }

    public SWTDummyState(SWTStateGraphImpl graph) {
        super(graph);
    }

}
