package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketConnector {

    /**
     * @return true means finished, false to continue with other connectors.
     */
    boolean connect(@NotNull  SocketChannel channel)
            throws IOException;

}
