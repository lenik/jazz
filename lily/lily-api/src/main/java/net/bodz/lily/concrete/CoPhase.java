package net.bodz.lily.concrete;

public abstract class CoPhase
        extends CoCode<CoPhase> {

    private static final long serialVersionUID = 1L;

    public CoPhase() {
        super();
    }

    public CoPhase(CoPhase parent) {
        super(parent);
    }

}
