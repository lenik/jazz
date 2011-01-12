package net.bodz.bas.flow;

import java.io.IOException;

/**
 * out sender -> dst-receiver
 */
public class WireOutPort
        extends AbstractOutPort {

    private String name;
    private IPortMeta meta;
    protected IReceiver dst;

    public WireOutPort(String name, IUnit unit, int index) {
        super(unit, index);
        this.name = name;
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    public IPortMeta getOutPortMeta() {
        if (meta == null)
            meta = createPortMeta();
        return meta;
    }

    @Override
    protected IPortMeta createPortMeta() {
        return new STPortMeta(name, Object.class);
    }

    @Override
    public IReceiver getDst() {
        return dst;
    }

    @Override
    public void setDst(IReceiver dst)
            throws IOException {
        this.dst = dst;
    }

    @Override
    public void send(Object data)
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
