package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketAccepter {

    /**
     * @return true means finished, no more accepter to be used.
     */
    boolean accept(@NotNull ServerSocketChannel serverChannel)
            throws IOException;

}
