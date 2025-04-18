package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.EventListener;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketAccepter
        extends EventListener {

    /**
     * @return true if accepted.
     */
    boolean accept(@NotNull ServerSocketChannel serverChannel)
            throws IOException;

}
