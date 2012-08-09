package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.flow.stream.IReceiver;

/**
 * in receiver -> dst-receiver
 */
public class WireInPort
        extends AbstractInPort {

    private String name;
    protected IReceiver dst;

    public WireInPort(String name, IUnit unit, int index, IReceiver dst) {
        super(unit, index);
        this.name = name;
        this.dst = dst;
    }

    @Override
    protected String getName() {
        return name;
    }

    public boolean isConnected() {
        return dst != null;
    }

    public IReceiver getDst() {
        return dst;
    }

    public void setDst(IReceiver dst) {
        this.dst = dst;
    }

    @Override
    public void recv(Object data)
            throws IOException {
        if (dst != null)
            dst.recv(data);
    }

    @Override
    public void flush()
            throws IOException {
        if (dst != null)
            dst.flush();
    }

}
