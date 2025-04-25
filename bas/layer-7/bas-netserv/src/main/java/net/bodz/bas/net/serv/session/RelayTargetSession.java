package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.IReadResult;
import net.bodz.bas.net.io.ISocketPoller;

public class RelayTargetSession
        extends AbstractSocketSession {

    RelayToSession source;
    // SocketChannel sourceChannel;

    public RelayTargetSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull RelayToSession source) {
        super(name, channel, poller);
        this.source = source;
    }

    @Override
    public IReadResult read(@NotNull SocketChannel channel)
            throws IOException {
        return buffer.read(channel);
    }

}
