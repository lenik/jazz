package net.bodz.bas.net.serv.session;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketReader;

public interface ISession
        extends ISocketReader {

    @NotNull
    String getSessionId();

    @NotNull
    SocketChannel getChannel();

    void close();

    boolean isClosed();

}
