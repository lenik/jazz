package net.bodz.bas.flow.unit;

public abstract class GenericUnit_nv
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
