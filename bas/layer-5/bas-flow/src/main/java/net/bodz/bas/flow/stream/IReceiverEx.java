package net.bodz.bas.flow.stream;

import java.io.IOException;

public interface IReceiverEx
        extends IReceiver {

    void recvNull()
            throws IOException;

    void recvUnknown(Object data)
            throws IOException;

}
