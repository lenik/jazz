package net.bodz.bas.flow.unit;


public abstract class GenericUnit_1n
        extends GenericUnit_1v {

    protected IOutPort[] outPorts;

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public IOutPort getOutPort(int outPortIndex) {
        return outPorts[outPortIndex];
    }

}
