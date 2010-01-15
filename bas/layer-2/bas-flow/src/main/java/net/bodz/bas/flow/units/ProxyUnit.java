package net.bodz.bas.flow.units;

import net.bodz.bas.flow.InPort;
import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow.Unit;
import net.bodz.bas.flow.UnitMeta;

public abstract class ProxyUnit implements Unit {

    protected final Unit proxy;
    private InPort[] inPorts;
    private OutPort[] outPorts;

    public ProxyUnit(Unit proxy) {
        this.proxy = proxy;
    }

    @Override
    public UnitMeta getUnitMeta() {
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

    protected abstract InPort getInPort(int portIndex, InPort proxyPort);

    protected abstract OutPort getOutPort(int portIndex, OutPort proxyPort);

    @Override
    public InPort getInPort(int portIndex) {
        if (inPorts == null)
            inPorts = new InPort[proxy.getInPorts()];
        InPort inPort = inPorts[portIndex];
        if (inPort == null) {
            InPort proxyInPort = proxy.getInPort(portIndex);
            inPort = getInPort(portIndex, proxyInPort);
            inPorts[portIndex] = inPort;
        }
        return inPort;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        if (outPorts == null)
            outPorts = new OutPort[proxy.getOutPorts()];
        OutPort outPort = outPorts[portIndex];
        if (outPort == null) {
            OutPort proxyOutPort = proxy.getOutPort(portIndex);
            outPort = getOutPort(portIndex, proxyOutPort);
            outPorts[portIndex] = outPort;
        }
        return outPort;
    }

}
