package net.bodz.bios;

import java.io.IOException;

public interface OutPort extends Port, Sender {

    PortMeta getOutPortMeta();

    int getOutPortIndex();

    Receiver getDst();

    void setDst(Receiver dst) throws IOException;

}
