package net.bodz.bios;

import java.io.IOException;

/**
 * in receiver -> dst-receiver
 */
public class WireInPort extends _InPort {

    private String name;
    private PortMeta meta;
    protected Receiver dst;

    public WireInPort(String name, Unit unit, int index, Receiver dst) {
        super(unit, index);
        this.name = name;
        this.dst = dst;
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    public PortMeta getInPortMeta() {
        if (meta == null)
            meta = createPortMeta();
        return meta;
    }

    @Override
    protected PortMeta createPortMeta() {
        return new STPortMeta(name, Object.class);
    }

    public boolean isConnected() {
        return dst != null;
    }

    public Receiver getDst() {
        return dst;
    }

    public void setDst(Receiver dst) {
        this.dst = dst;
    }

    @Override
    public void recv(Object data) throws IOException {
        if (dst != null)
            dst.recv(data);
    }

    @Override
    public void flush() throws IOException {
        if (dst != null)
            dst.flush();
    }

}
