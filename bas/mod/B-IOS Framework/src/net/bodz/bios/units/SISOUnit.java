package net.bodz.bios.units;

import java.io.IOException;

import net.bodz.bas.lang.a.TypeNote;
import net.bodz.bios.OutPort;
import net.bodz.bios.PortMeta;
import net.bodz.bios.Receiver;
import net.bodz.bios.STPortMeta;
import net.bodz.bios.Unit;

public abstract class SISOUnit extends SIUnit implements OutPort {

    protected Receiver dst;

    @Override
    @TypeNote(Unit.class)
    public int getOutPorts() {
        return 1;
    }

    @Override
    @TypeNote(Unit.class)
    public OutPort getOutPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("out " + portIndex);
        return this;
    }

    @Override
    @TypeNote(OutPort.class)
    public PortMeta getOutPortMeta() {
        return new STPortMeta("out", Object.class);
    }

    @Override
    @TypeNote(OutPort.class)
    public int getOutPortIndex() {
        return 0;
    }

    @Override
    @TypeNote(OutPort.class)
    public Receiver getDst() {
        return dst;
    }

    @Override
    @TypeNote(OutPort.class)
    public void setDst(Receiver dst) throws IOException {
        this.dst = dst;
    }

    @Override
    @TypeNote(OutPort.class)
    public void send(Object data) throws IOException {
        if (dst != null)
            dst.recv(data);
    }

}
