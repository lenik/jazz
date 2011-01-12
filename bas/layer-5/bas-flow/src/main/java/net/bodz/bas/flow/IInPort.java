package net.bodz.bas.flow;

import java.io.IOException;

public interface IInPort
        extends IPort, IReceiver {

    IPortMeta getInPortMeta();

    int getInPortIndex();

    void addSrc(IOutPort srcPort)
            throws IOException;

    void removeSrc(IOutPort srcPort)
            throws IOException;

}
