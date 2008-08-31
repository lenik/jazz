package net.bodz.bios;

import java.io.IOException;

public interface InPort extends Port, Receiver {

    PortMeta getInPortMeta();

    int getInPortIndex();

    void addSrc(OutPort srcPort) throws IOException;

    void removeSrc(OutPort srcPort) throws IOException;

}
