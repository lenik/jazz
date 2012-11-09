package net.bodz.bas.flow.unit.arch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.unit.GenericUnit_1v;
import net.bodz.bas.flow.unit.IOutPort;
import net.bodz.bas.flow.unit.WireOutPort;
import net.bodz.bas.meta.decl.Stateless;

@Stateless
public class TeeUnit
        extends GenericUnit_1v {

    private List<IOutPort> outPorts;

    public TeeUnit() {
        outPorts = new ArrayList<IOutPort>();
    }

    public TeeUnit(int initialPorts)
            throws IOException {
        outPorts = new ArrayList<IOutPort>(initialPorts);
        for (int i = 0; i < initialPorts; i++)
            addOutPort();
    }

    @Override
    public int getOutPorts() {
        return outPorts.size();
    }

    @Override
    public IOutPort getOutPort(int outPortIndex) {
        return outPorts.get(outPortIndex);
    }

    int displayIndex;

    public void addOutPort(IReceiver dst)
            throws IOException {
        int newIndex = outPorts.size();
        String name = "out-" + displayIndex++;
        IOutPort port = new WireOutPort(name, this, newIndex);
        port.setDst(dst);
        outPorts.add(port);
    }

    public void addOutPort()
            throws IOException {
        addOutPort(null);
    }

    public void removeOutPort(int index)
            throws IOException {
        outPorts.remove(index);
    }

    /**
     * remove all out ports, and reset index number for new added port.
     */
    public void resetOutPorts() {
        displayIndex = 0;
        outPorts.clear();
    }

    /**
     * Tee is state-less.
     * 
     * @see #resetOutPorts()
     */
    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        for (IOutPort outPort : outPorts)
            outPort.flush();
    }

    @Override
    public void recv(Object data)
            throws IOException {
        for (IOutPort outPort : outPorts)
            outPort.send(data);
    }

}
