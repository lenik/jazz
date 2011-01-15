package net.bodz.bas.flow.units;

import net.bodz.bas.flow.AbstractUnit;
import net.bodz.bas.flow.IOutPort;

public abstract class MOUnit
        extends AbstractUnit {

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        return null;
    }

}
