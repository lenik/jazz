package net.bodz.bas.flow.unit;

import java.io.IOException;

public class AsyncUnit
        extends ProxyUnit {

    public AsyncUnit(IUnit proxy) {
        super(proxy);
    }

    @Override
    protected IInPort getInPort(int portIndex, IInPort proxyPort) {
        // return new AsyncPort(proxyPort);
        return null;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        return proxy.getOutPort(portIndex);
    }

    @Override
    protected IOutPort getOutPort(int portIndex, IOutPort proxyPort) {
        return proxyPort;
    }

    @Override
    public void reset()
            throws IOException {
        proxy.reset();
    }

    @Override
    public void flush()
            throws IOException {
        proxy.flush();
    }

}
