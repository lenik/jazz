package net.bodz.bas.flow.unit;

public abstract class GenericUnit_vn
        extends AbstractUnit {

    private IOutPort[] outPorts;

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        return outPorts[portIndex];
    }

}
