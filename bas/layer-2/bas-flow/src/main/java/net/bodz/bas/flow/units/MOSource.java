package net.bodz.bas.flow.units;

import net.bodz.bas.flow.InPort;

public abstract class MOSource extends MOUnit implements Source {

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public InPort getInPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
