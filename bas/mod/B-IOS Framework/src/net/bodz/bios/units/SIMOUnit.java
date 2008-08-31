package net.bodz.bios.units;

import net.bodz.bios.OutPort;

public abstract class SIMOUnit extends SIUnit {

    protected OutPort[] outPorts;

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public OutPort getOutPort(int outPortIndex) {
        return outPorts[outPortIndex];
    }

}
