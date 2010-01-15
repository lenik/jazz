package net.bodz.bas.flow.units;

import net.bodz.bas.flow.InPort;
import net.bodz.bas.flow._Unit;

public abstract class MIUnit extends _Unit {

    private InPort[] inPorts;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public InPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

}
