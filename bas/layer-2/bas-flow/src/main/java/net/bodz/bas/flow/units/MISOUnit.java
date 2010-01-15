package net.bodz.bas.flow.units;

import net.bodz.bas.flow.InPort;
import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow.Sender;
import net.bodz.bas.flow.Unit;

public abstract class MISOUnit extends SOUnit implements Unit, Sender {

    private InPort[] inPorts;
    private OutPort outPort;

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
