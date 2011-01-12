package net.bodz.bas.flow.units;

import net.bodz.bas.flow.IInPort;
import net.bodz.bas.flow.IOutPort;
import net.bodz.bas.flow.ISender;
import net.bodz.bas.flow.IUnit;

public abstract class MISOUnit
        extends SOUnit
        implements IUnit, ISender {

    private IInPort[] inPorts;
    private IOutPort outPort;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public int getOutPorts() {
        return 1;
    }

    @Override
    public IInPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

    @Override
    public IOutPort getOutPort(int outPortIndex) {
        return outPort;
    }

}
