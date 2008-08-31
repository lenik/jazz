package net.bodz.bios.units;

import java.io.IOException;

import net.bodz.bas.lang.annotations.TypeNote;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bios.InPort;
import net.bodz.bios.OutPort;
import net.bodz.bios.Port;
import net.bodz.bios.PortMeta;
import net.bodz.bios.Receiver;
import net.bodz.bios.ReceiverEx;
import net.bodz.bios.STPortMeta;
import net.bodz.bios.Unit;
import net.bodz.bios._Unit;
import net.bodz.bios.util.RecvUtil;

public abstract class SIUnit extends _Unit implements InPort, ReceiverEx {

    @Override
    @TypeNote(Unit.class)
    public int getInPorts() {
        return 1;
    }

    @Override
    @TypeNote(Unit.class)
    public InPort getInPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("in " + portIndex);
        return this;
    }

    @Override
    @TypeNote(Port.class)
    public Unit getUnit() {
        return this;
    }

    @Override
    @TypeNote(InPort.class)
    public PortMeta getInPortMeta() {
        return new STPortMeta("in", Object.class);
    }

    @Override
    @TypeNote(InPort.class)
    public int getInPortIndex() {
        return 0;
    }

    @Override
    @TypeNote(InPort.class)
    public void addSrc(OutPort srcPort) throws IOException {
    }

    @Override
    @TypeNote(InPort.class)
    public void removeSrc(OutPort srcPort) throws IOException {
    }

    @Override
    @TypeNote(Receiver.class)
    public void recv(Object data) throws IOException {
        RecvUtil.recvEx(this, data);
    }

    @Override
    @TypeNote(ReceiverEx.class)
    public void recvNull() throws IOException {
        throw new NullPointerException("recvNull");
    }

    @Override
    @TypeNote(ReceiverEx.class)
    public void recvUnknown(Object data) throws IOException {
        throw new OutOfDomainException("type", data.getClass());
    }

}
