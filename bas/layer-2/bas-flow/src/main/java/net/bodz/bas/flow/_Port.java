package net.bodz.bas.flow;

import net.bodz.bas.flow.util.Naming;

public abstract class _Port implements Port {

    protected Unit unit;

    public _Port(Unit unit) {
        this.unit = unit;
    }

    protected String getName() {
        return Naming.getDefaultName(this);
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

}
