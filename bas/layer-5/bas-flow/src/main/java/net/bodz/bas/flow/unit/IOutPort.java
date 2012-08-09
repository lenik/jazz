package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.stream.ISender;
import net.bodz.bas.flow.unit.metadata.IPortMeta;

public interface IOutPort
        extends IPort, ISender {

    IPortMeta getOutPortMeta();

    int getOutPortIndex();

    IReceiver getDst();

    void setDst(IReceiver dst)
            throws IOException;

}
