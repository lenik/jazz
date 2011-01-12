package net.bodz.bas.flow.units;

import net.bodz.bas.flow.IOutPort;

public abstract class SISinkUnit
        extends SIUnit
        implements ISinkUnit {

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
