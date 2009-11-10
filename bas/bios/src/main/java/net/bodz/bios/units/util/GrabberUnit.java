package net.bodz.bios.units.util;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import net.bodz.bios.InPort;
import net.bodz.bios.OutPort;
import net.bodz.bios.Receiver;
import net.bodz.bios.units.SISOUnit;

/**
 * {@link OutPort} grabber.
 */
public class GrabberUnit extends SISOUnit {

    private OutPort srcPort;
    private Collection<Object> buffer;

    GrabberUnit(OutPort outPort, Collection<Object> collection) throws IOException {
        Receiver dst0 = outPort.getDst();
        outPort.setDst(this);
        this.setDst(dst0);
        setBuffer(collection);
    }

    GrabberUnit(OutPort outPort) throws IOException {
        this(outPort, new LinkedList<Object>());
    }

    public OutPort getSrc() {
        return srcPort;
    }

    @Override
    public void addSrc(OutPort srcPort) {
        this.srcPort = srcPort;
    }

    @Override
    public void removeSrc(OutPort srcPort) {
        this.srcPort = null;
    }

    /**
     * clear the grabber buffer
     */
    @Override
    public void reset() throws IOException {
        buffer.clear();
    }

    @Override
    public void flush() throws IOException {
        if (dst != null)
            dst.flush();
    }

    @Override
    public void recv(Object data) throws IOException {
        buffer.add(data);
        send(data);
    }

    public Collection<Object> getBuffer() {
        return buffer;
    }

    public void setBuffer(Collection<Object> collection) {
        this.buffer = collection;
    }

    public Collection<Object> detach() throws IOException {
        srcPort.setDst(this.dst);
        return buffer;
    }

    public static GrabberUnit connect(OutPort src, InPort dst) throws IOException {
        src.setDst(dst);
        return new GrabberUnit(src);
    }

    public static GrabberUnit insert(OutPort src) throws IOException {
        return new GrabberUnit(src);
    }

}
