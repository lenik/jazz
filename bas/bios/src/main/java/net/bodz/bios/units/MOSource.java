package net.bodz.bios.units;

import net.bodz.bios.InPort;

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
