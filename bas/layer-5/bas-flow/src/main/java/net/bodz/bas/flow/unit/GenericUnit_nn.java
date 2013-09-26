package net.bodz.bas.flow.unit;

public abstract class GenericUnit_nn
        extends GenericUnit_nv {

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
