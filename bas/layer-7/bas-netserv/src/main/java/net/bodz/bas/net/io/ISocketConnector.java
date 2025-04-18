package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.EventListener;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketConnector
        extends EventListener {

    /**
     * @return true means finished, false to continue with other connectors.
     */
    boolean connect(@NotNull SocketChannel channel)
            throws IOException;

}
