package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public class RelayTargetSession
        extends AbstractSocketSession {

    RelayToSession source;
    // SocketChannel sourceChannel;

    public RelayTargetSession(@NotNull String id, @NotNull SocketChannel channel, @NotNull RelayToSession source) {
        super(channel);
        this.source = source;
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        return -1;
    }

}
