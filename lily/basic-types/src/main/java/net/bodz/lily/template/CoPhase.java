package net.bodz.lily.template;

import net.bodz.lily.model.base.CoCode;

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
