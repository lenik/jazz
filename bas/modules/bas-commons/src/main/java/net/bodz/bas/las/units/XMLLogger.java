package net.bodz.bas.las.units;

import net.bodz.bas.las.LasUnit;
import net.bodz.bas.las._LasUnit;

public class XMLLogger extends _LasUnit {

    public XMLLogger(LasUnit prev) {
        super(prev);
    }

    @Override
    protected boolean _enter(Object... args) {
        return false;
    }

    @Override
    protected void _leave() {
    }

}
