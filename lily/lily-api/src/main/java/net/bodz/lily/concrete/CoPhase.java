package net.bodz.lily.concrete;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.lily.meta.FieldGroupVue;

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
