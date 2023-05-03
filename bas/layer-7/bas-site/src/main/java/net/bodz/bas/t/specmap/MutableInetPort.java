package net.bodz.bas.t.specmap;

public abstract class MutableInetPort
        implements
            IMutableInetPort {

    int maskBits;
    int port;

    @Override
    public int getMaskBits() {
        return maskBits;
    }

    @Override
    public void setMaskBits(int maskBits) {
        this.maskBits = maskBits;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

}
