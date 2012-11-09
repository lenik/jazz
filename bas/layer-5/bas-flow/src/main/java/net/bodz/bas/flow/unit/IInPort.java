package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.unit.metadata.IPortMetadata;

public interface IInPort
        extends IPort, IReceiver {

    IPortMetadata getInPortMeta();

    int getInPortIndex();

    void addSrc(IOutPort srcPort)
            throws IOException;

    void removeSrc(IOutPort srcPort)
            throws IOException;

}
