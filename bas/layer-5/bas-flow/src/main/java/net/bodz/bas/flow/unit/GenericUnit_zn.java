package net.bodz.bas.flow.unit;


public abstract class GenericUnit_zn
        extends AbstractSourceUnit {

    private IOutPort[] outPorts;

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public IInPort getInPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int getOutPorts() {
        return outPorts.length;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        return outPorts[portIndex];
    }

}
