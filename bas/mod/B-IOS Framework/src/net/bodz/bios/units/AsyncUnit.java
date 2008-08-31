package net.bodz.bios.units;

import java.io.IOException;

import net.bodz.bios.InPort;
import net.bodz.bios.OutPort;
import net.bodz.bios.Unit;

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
