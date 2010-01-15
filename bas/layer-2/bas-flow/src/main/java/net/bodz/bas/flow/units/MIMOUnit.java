package net.bodz.bas.flow.units;

import net.bodz.bas.flow.OutPort;

public abstract class MIMOUnit extends MIUnit {

    private OutPort[] outPorts;

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public OutPort getOutPort(int outPortIndex) {
        return outPorts[outPortIndex];
    }

}
