package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.flow.InPort;
import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow.Unit;

public class AsyncUnit extends ProxyUnit {

    public AsyncUnit(Unit proxy) {
        super(proxy);
    }

    @Override
    protected InPort getInPort(int portIndex, InPort proxyPort) {
        // return new AsyncPort(proxyPort);
        return null;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        return proxy.getOutPort(portIndex);
    }

    @Override
    protected OutPort getOutPort(int portIndex, OutPort proxyPort) {
        return proxyPort;
    }

    @Override
    public void reset() throws IOException {
        proxy.reset();
    }

    @Override
    public void flush() throws IOException {
        proxy.flush();
    }

}
