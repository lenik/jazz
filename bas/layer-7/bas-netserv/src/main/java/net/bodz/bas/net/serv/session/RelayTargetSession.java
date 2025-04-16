package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public class RelayTargetSession
        extends AbstractSocketSession {

    RelaySession source;
    // SocketChannel sourceChannel;

    public RelayTargetSession(@NotNull String id, @NotNull SocketChannel channel, @NotNull RelaySession source) {
        super(channel);
        this.source = source;
    }

    @Override
    public boolean read(@NotNull SocketChannel channel)
            throws IOException, ParseException {
        return true;
    }

}
