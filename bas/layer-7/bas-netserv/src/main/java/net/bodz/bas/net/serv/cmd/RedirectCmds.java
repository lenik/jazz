package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.session.ISession;
import net.bodz.bas.net.serv.session.RelaySession;

public class RedirectCmds
        extends AbstractNioCommandProvider {

    @NotNull
    final ISessionManager sessionManager;

    @NotNull
    final ISession session;

    @NotNull
    final ISocketPoller poller;

    public RedirectCmds(@NotNull SocketChannel channel, @NotNull ISessionManager sessionManager, @NotNull ISession session, @NotNull ISocketPoller poller) {
        super(channel);
        this.sessionManager = sessionManager;
        this.session = session;
        this.poller = poller;
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "connect":
                connect(cmd);
                return true;
        }
        return false;
    }

    public void connect(Command cmd)
            throws IOException {
        if (cmd.isNoArgument()) {
            error("Expect relay port.");
            return;
        }

        String portStr = cmd.getArgument(1);
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            error("Invalid port number: " + portStr);
            return;
        }

        String host = "localhost";
        if (cmd.isArgumentPresent(2))
            host = cmd.getArgument(2);

        connect(host, port);
    }

    public void connect(String host, int port)
            throws IOException {
        InetSocketAddress destAddr = new InetSocketAddress(host, port);
        String id = session.getSessionId();
        RelaySession newSession = new RelaySession(id, channel, destAddr, poller);
        sessionManager.replaceSession(this.session, newSession);
    }

}
