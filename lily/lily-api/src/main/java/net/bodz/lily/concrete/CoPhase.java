package net.bodz.lily.concrete;

import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;

@FieldGroupVue
@TsTyped
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
