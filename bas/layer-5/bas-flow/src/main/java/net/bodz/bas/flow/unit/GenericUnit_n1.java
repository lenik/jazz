package net.bodz.bas.flow.unit;

import net.bodz.bas.flow.stream.ISender;

public abstract class GenericUnit_n1
        extends GenericUnit_v1
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
