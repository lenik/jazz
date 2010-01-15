package net.bodz.bas.flow.units;

import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow._Unit;

public abstract class MOUnit extends _Unit {

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        return null;
    }

}
