package net.bodz.bas.flow.units;

import net.bodz.bas.flow.IOutPort;

public abstract class MIMOUnit
        extends MIUnit {

    private IOutPort[] outPorts;

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public IOutPort getOutPort(int outPortIndex) {
        return outPorts[outPortIndex];
    }

}
