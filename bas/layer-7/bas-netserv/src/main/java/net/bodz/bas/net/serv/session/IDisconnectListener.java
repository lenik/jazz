package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface IDisconnectListener {

    void onDisconnect(SocketChannel channel)
            throws IOException;

}
