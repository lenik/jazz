package net.bodz.bas.flow.units;

import net.bodz.bas.flow.OutPort;

public abstract class MISinkUnit extends MIUnit implements SinkUnit {

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
