package net.bodz.bas.flow.unit;

public abstract class GenericUnit_nz
        extends AbstractSinkUnit
        implements ISinkUnit {

    private IInPort[] inPorts;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public IInPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
