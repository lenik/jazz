package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import net.bodz.bas.cli.Command;
import net.bodz.bas.cli.IArgQueue;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISettings;
import net.bodz.bas.net.serv.session.ISocketSession;
import net.bodz.bas.net.serv.session.RelayToSession;
import net.bodz.bas.net.serv.session.SendSession;

public class ForwardCmds
        extends AbstractNioCommandProvider {

    static final Logger logger = LoggerFactory.getLogger(ForwardCmds.class);

    ISettings settings;
    ISocketPoller poller;
    Consumer<ISocketSession> applier;

    public ForwardCmds(@NotNull SocketChannel channel, ISettings settings, @NotNull ISocketPoller poller, @NotNull Consumer<ISocketSession> applier) {
        super(channel);
        this.settings = settings;
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

            case "send":
                send(cmd);
                return true;
        }
        return false;
    }

    /**
     * connect PORT HOST=localhost
     */
    void connect(IArgQueue args)
            throws IOException {
        String portStr = args.shift();
        if (portStr == null) {
            error("Expect relay port.");
            return;
        }

        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            error("Invalid port number: " + portStr);
            return;
        }

        String host = args.shift();
        connect(port, host);
    }

    public void connect(int port) {
        connect(port, null);
    }

    public void connect(int port, String host) {
        if (host == null)
            host = "localhost";
        InetSocketAddress destAddr = new InetSocketAddress(host, port);
        SocketChannel targetChannel;
        try {
            targetChannel = SocketChannel.open(destAddr);
        } catch (IOException e) {
            logger.error(e, "error open channel to " + destAddr);
            return;
        }

        RelayToSession newSession = null;
        try {
            newSession = new RelayToSession(channel, poller, targetChannel);
        } catch (IOException e) {
            logger.error(e, "error setup Relay-To session");
            return;
        }

        applier.accept(newSession);
    }

    /**
     * send [HOST:]PORT message...
     */
    void send(IArgQueue args)
            throws IOException {
        String hostPort = args.shift();
        if (hostPort == null) {
            error("Expect [host:]port.");
            return;
        }

        String host = "localhost";
        String portStr;
        int lastColon = hostPort.lastIndexOf(':');
        if (lastColon == -1) {
            portStr = hostPort;
        } else {
            host = hostPort.substring(0, lastColon);
            portStr = hostPort.substring(lastColon + 1);
        }

        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            error("Invalid port number: " + portStr);
            return;
        }

        String message = args.toString(" ");
        send(host, port, message + "\n");
    }

    Map<SocketChannel, SendSession> sendSessions = new LinkedHashMap<>();

    void send(String host, int port, String message)
            throws IOException {
        InetSocketAddress targetAddr = new InetSocketAddress(host, port);
        SendSession sendSession = new SendSession(channel, poller);

        if (settings != null)
            sendSession.copySettings(settings);


        sendSessions.put(sendSession.getTargetChannel(), sendSession);

        sendSession.connect(targetAddr, c -> {
            sendSessions.remove(c);
        });

        sendSession.sendToTarget(message);
    }

}
