package net.bodz.bas.net.serv.session;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketReader;

public interface ISocketSession
        extends ISocketReader {

    @NotNull
    SocketChannel getChannel();

    void close();

    boolean isClosed();

}
