package net.bodz.bas.flow.unit.arch;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.unit.IInPort;
import net.bodz.bas.flow.unit.IOutPort;

/**
 * {@link IOutPort} grabber.
 */
public class GrabberUnit
        extends AbstractFilterUnit {

    private IOutPort srcPort;
    private Collection<Object> buffer;

    GrabberUnit(IOutPort outPort, Collection<Object> collection)
            throws IOException {
        IReceiver dst0 = outPort.getDst();
        outPort.setDst(this);
        this.setDst(dst0);
        setBuffer(collection);
    }

    GrabberUnit(IOutPort outPort)
            throws IOException {
        this(outPort, new LinkedList<Object>());
    }

    public IOutPort getSrc() {
        return srcPort;
    }

    @Override
    public void addSrc(IOutPort srcPort) {
        this.srcPort = srcPort;
    }

    @Override
    public void removeSrc(IOutPort srcPort) {
        this.srcPort = null;
    }

    /**
     * clear the grabber buffer
     */
    @Override
    public void reset()
            throws IOException {
        buffer.clear();
    }

    @Override
    public void flush()
            throws IOException {
        if (dst != null)
            dst.flush();
    }

    @Override
    public void recv(Object data)
            throws IOException {
        buffer.add(data);
        send(data);
    }

    public Collection<Object> getBuffer() {
        return buffer;
    }

    public void setBuffer(Collection<Object> collection) {
        this.buffer = collection;
    }

    public Collection<Object> detach()
            throws IOException {
        srcPort.setDst(this.dst);
        return buffer;
    }

    public static GrabberUnit connect(IOutPort src, IInPort dst)
            throws IOException {
        src.setDst(dst);
        return new GrabberUnit(src);
    }

    public static GrabberUnit insert(IOutPort src)
            throws IOException {
        return new GrabberUnit(src);
    }

}
