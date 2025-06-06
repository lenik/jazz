package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.EventListener;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketReader
        extends EventListener {

    /**
     * @return number of bytes have been read.
     */
    IReadResult read(@NotNull SocketChannel channel)
            throws IOException;

}
