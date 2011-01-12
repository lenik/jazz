package net.bodz.bas.flow.units;

import net.bodz.bas.flow.IInPort;
import net.bodz.bas.flow.IOutPort;
import net.bodz.bas.flow.IUnit;
import net.bodz.bas.flow.IUnitMeta;

public abstract class ProxyUnit
        implements IUnit {

    protected final IUnit proxy;
    private IInPort[] inPorts;
    private IOutPort[] outPorts;

    public ProxyUnit(IUnit proxy) {
        this.proxy = proxy;
    }

    @Override
    public IUnitMeta getUnitMeta() {
        return proxy.getUnitMeta();
    }

    @Override
    public int getInPorts() {
        return proxy.getInPorts();
    }

    @Override
    public int getOutPorts() {
        return proxy.getOutPorts();
    }

    protected abstract IInPort getInPort(int portIndex, IInPort proxyPort);

    protected abstract IOutPort getOutPort(int portIndex, IOutPort proxyPort);

    @Override
    public IInPort getInPort(int portIndex) {
        if (inPorts == null)
            inPorts = new IInPort[proxy.getInPorts()];
        IInPort inPort = inPorts[portIndex];
        if (inPort == null) {
            IInPort proxyInPort = proxy.getInPort(portIndex);
            inPort = getInPort(portIndex, proxyInPort);
            inPorts[portIndex] = inPort;
        }
        return inPort;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        if (outPorts == null)
            outPorts = new IOutPort[proxy.getOutPorts()];
        IOutPort outPort = outPorts[portIndex];
        if (outPort == null) {
            IOutPort proxyOutPort = proxy.getOutPort(portIndex);
            outPort = getOutPort(portIndex, proxyOutPort);
            outPorts[portIndex] = outPort;
        }
        return outPort;
    }

}
