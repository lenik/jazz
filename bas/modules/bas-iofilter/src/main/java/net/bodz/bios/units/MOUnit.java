package net.bodz.bios.units;

import net.bodz.bios.OutPort;
import net.bodz.bios._Unit;

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
