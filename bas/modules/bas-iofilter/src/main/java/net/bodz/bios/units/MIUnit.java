package net.bodz.bios.units;

import net.bodz.bios.InPort;
import net.bodz.bios._Unit;

public abstract class MIUnit extends _Unit {

    private InPort[] inPorts;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public InPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

}
