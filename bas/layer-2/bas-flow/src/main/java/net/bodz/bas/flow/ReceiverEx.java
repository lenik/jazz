package net.bodz.bas.flow;

import java.io.IOException;

public interface ReceiverEx extends Receiver {

    void recvNull() throws IOException;

    void recvUnknown(Object data) throws IOException;

}
