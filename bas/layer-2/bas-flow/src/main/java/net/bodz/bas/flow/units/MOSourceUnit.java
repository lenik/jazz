package net.bodz.bas.flow.units;

import net.bodz.bas.flow.IInPort;

public abstract class MOSourceUnit
        extends MOUnit
        implements ISourceUnit {

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public IInPort getInPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
