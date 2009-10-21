package net.bodz.bios.units;

import net.bodz.bios.InPort;
import net.bodz.bios.OutPort;
import net.bodz.bios.Sender;
import net.bodz.bios.Unit;

public abstract class MISOUnit extends SOUnit implements Unit, Sender {

    private InPort[] inPorts;
    private OutPort  outPort;

    @Override
    public int getInPorts() {
        return inPorts.length;
    }

    @Override
    public int getOutPorts() {
        return 1;
    }

    @Override
    public InPort getInPort(int inPortIndex) {
        return inPorts[inPortIndex];
    }

    @Override
    public OutPort getOutPort(int outPortIndex) {
        return outPort;
    }

}
