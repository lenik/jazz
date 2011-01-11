package net.bodz.bas.flow;

import java.io.IOException;

public interface IOutPort
        extends IPort, ISender {

    IPortMeta getOutPortMeta();

    int getOutPortIndex();

    IReceiver getDst();

    void setDst(IReceiver dst)
            throws IOException;

}
