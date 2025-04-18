package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.EventListener;

import net.bodz.bas.meta.decl.NotNull;

public interface ISocketWriter
        extends EventListener {

    /**
     * @return number of bytes have been writtern.
     */
    long write(@NotNull SocketChannel channel)
            throws IOException;

}
