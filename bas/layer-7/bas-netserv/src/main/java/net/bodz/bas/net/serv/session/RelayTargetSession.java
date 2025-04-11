package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.AbstractSession;

public class RelayTargetSession
        extends AbstractSession {

    RelaySession source;
    // SocketChannel sourceChannel;

    public RelayTargetSession(@NotNull String id, @NotNull SocketChannel channel, @NotNull RelaySession source) {
        super(id, channel);
        this.source = source;
    }

    @Override
    public boolean read(SocketChannel channel)
            throws IOException, ParseException {
        return true;
    }

}
