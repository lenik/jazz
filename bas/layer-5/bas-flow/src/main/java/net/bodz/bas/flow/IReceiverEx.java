package net.bodz.bas.flow;

import java.io.IOException;

public interface IReceiverEx
        extends IReceiver {

    void recvNull()
            throws IOException;

    void recvUnknown(Object data)
            throws IOException;

}
