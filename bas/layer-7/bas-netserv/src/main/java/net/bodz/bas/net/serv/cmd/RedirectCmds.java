package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.session.ISocketSession;
import net.bodz.bas.net.serv.session.RelaySession;

public class RedirectCmds
        extends AbstractNioCommandProvider {

    @NotNull
    final ISocketPoller poller;

    @NotNull
    final Consumer<ISocketSession> applier;

    public RedirectCmds(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull Consumer<ISocketSession> applier) {
        super(channel);
        this.poller = poller;
        this.applier = applier;
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

        String host = cmd.getArgument(2, null);
        connect(port, host);
    }

    public void connect(int port)
            throws IOException {
        connect(port, null);
    }

    public void connect(int port, String host)
            throws IOException {
        if (host == null)
            host = "localhost";
        InetSocketAddress destAddr = new InetSocketAddress(host, port);
        RelaySession newSession = new RelaySession(channel, destAddr, poller);
        applier.accept(newSession);
    }

}
