package net.bodz.bios.units;

import net.bodz.bios.OutPort;

public abstract class MISink extends MIUnit implements Sink {

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        throw new IndexOutOfBoundsException();
    }

}
