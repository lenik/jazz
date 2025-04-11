package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface ISocketReader {

    /**
     * @return true means finished, false to continue with other readers.
     */
    boolean read(@NotNull SocketChannel channel)
            throws IOException, ParseException;

}
