package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

public interface ISocketWriter {

    /**
     * @return true means finished, false to continue with other writers.
     */
    boolean write(@NotNull SocketChannel channel)
            throws IOException;

}
