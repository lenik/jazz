package net.bodz.bas.flow.units;

import net.bodz.bas.flow.AbstractUnit;
import net.bodz.bas.flow.IInPort;

public abstract class MIUnit
        extends AbstractUnit {

    private IInPort[] inPorts;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public IInPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

}
