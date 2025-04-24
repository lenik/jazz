package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.function.Predicate;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;

public class SendSession
        extends PeerSession {

    static final Logger logger = LoggerFactory.getLogger(SendSession.class);

    Predicate<Character> receiveCharUntil;

    public SendSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(name, channel, poller);
    }

    public SendSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel)
            throws IOException {
        super(name, channel, poller, targetChannel);
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        throw new NotImplementedException();
    }

    @Override
    protected void readTargetBuffer() {
        String message = target.getReadString();
        target.clearReadBuffer();
        buffer.printLine(message);
    }

}
