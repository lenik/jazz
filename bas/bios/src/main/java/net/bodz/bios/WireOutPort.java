package net.bodz.bios;

import java.io.IOException;

/**
 * out sender -> dst-receiver
 */
public class WireOutPort extends _OutPort {

    private String name;
    private PortMeta meta;
    protected Receiver dst;

    public WireOutPort(String name, Unit unit, int index) {
        super(unit, index);
        this.name = name;
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    public PortMeta getOutPortMeta() {
        if (meta == null)
            meta = createPortMeta();
        return meta;
    }

    @Override
    protected PortMeta createPortMeta() {
        return new STPortMeta(name, Object.class);
    }

    @Override
    public Receiver getDst() {
        return dst;
    }

    @Override
    public void setDst(Receiver dst) throws IOException {
        this.dst = dst;
    }

    @Override
    public void send(Object data) throws IOException {
        if (dst != null)
            dst.recv(data);
    }

    @Override
    public void flush() throws IOException {
        if (dst != null)
            dst.flush();
    }

}
