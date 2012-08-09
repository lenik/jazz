package net.bodz.bas.flow.unit;

import net.bodz.bas.c.type.TypeName;

public abstract class AbstractPort
        implements IPort {

    protected IUnit unit;

    public AbstractPort(IUnit unit) {
        this.unit = unit;
    }

    protected String getName() {
        return TypeName.friendly_name(getClass());
    }

    @Override
    public IUnit getUnit() {
        return unit;
    }

}
