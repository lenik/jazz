package net.bodz.bas.flow;

import net.bodz.bas.flow.util.Naming;

public abstract class AbstractPort
        implements IPort {

    protected IUnit unit;

    public AbstractPort(IUnit unit) {
        this.unit = unit;
    }

    protected String getName() {
        return Naming.getDefaultName(this);
    }

    @Override
    public IUnit getUnit() {
        return unit;
    }

}
