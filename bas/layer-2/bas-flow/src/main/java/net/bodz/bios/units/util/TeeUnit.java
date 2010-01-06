package net.bodz.bios.units.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bios.OutPort;
import net.bodz.bios.Receiver;
import net.bodz.bios.Stateless;
import net.bodz.bios.WireOutPort;
import net.bodz.bios.units.SIUnit;

@Stateless
public class TeeUnit extends SIUnit {

    private List<OutPort> outPorts;

    public TeeUnit() {
        outPorts = new ArrayList<OutPort>();
    }

    public TeeUnit(int initialPorts) throws IOException {
        outPorts = new ArrayList<OutPort>(initialPorts);
        for (int i = 0; i < initialPorts; i++)
            addOutPort();
    }

    @Override
    public int getOutPorts() {
        return outPorts.size();
    }

    @Override
    public OutPort getOutPort(int outPortIndex) {
        return outPorts.get(outPortIndex);
    }

    int displayIndex;

    public void addOutPort(Receiver dst) throws IOException {
        int newIndex = outPorts.size();
        String name = "out-" + displayIndex++; 
        OutPort port = new WireOutPort(name, this, newIndex);
        port.setDst(dst);
        outPorts.add(port);
    }

    public void addOutPort() throws IOException {
        addOutPort(null);
    }

    public void removeOutPort(int index) throws IOException {
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
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
        for (OutPort outPort : outPorts)
            outPort.flush();
    }

    @Override
    public void recv(Object data) throws IOException {
        for (OutPort outPort : outPorts)
            outPort.send(data);
    }

}
