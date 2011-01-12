package net.bodz.bas.flow;

import java.io.IOException;

/**
 * in receiver -> dst-receiver
 */
public class WireInPort
        extends AbstractInPort {

    private String name;
    private IPortMeta meta;
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

    @Override
    public IPortMeta getInPortMeta() {
        if (meta == null)
            meta = createPortMeta();
        return meta;
    }

    @Override
    protected IPortMeta createPortMeta() {
        return new STPortMeta(name, Object.class);
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
