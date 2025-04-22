package net.bodz.bas.net.serv;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.session.DirectiveSocketSession;

public class TestSession
        extends DirectiveSocketSession {

    public TestSession(SocketChannel channel, @NotNull ISocketPoller poller) {
        super(channel, poller);
    }

}
