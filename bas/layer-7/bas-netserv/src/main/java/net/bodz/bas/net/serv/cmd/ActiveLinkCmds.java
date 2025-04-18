package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.cli.Command;
import net.bodz.bas.cli.IArgQueue;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.session.ISocketSession;
import net.bodz.bas.std.StdService;
import net.bodz.bas.std.TransportType;

public class ActiveLinkCmds
        extends AbstractNioCommandProvider {

    final ISocketPoller poller;
    final Consumer<ISocketSession> applier;

    public ActiveLinkCmds(SocketChannel channel, ISocketPoller poller, Consumer<ISocketSession> applier) {
        super(channel);
        this.poller = poller;
        this.applier = applier;
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "open":
                open(cmd);
                return true;
            case "close":
                close(cmd);
                return true;
            case "status":
                status(cmd);
                return true;
        }
        return false;
    }

    void open(IArgQueue args)
            throws IOException {
        int count = 1;

        String head = args.shift();
        if (head != null && head.endsWith("x")) {
            String s = head.substring(0, head.length() - 1);
            if (StringPred.isInteger(s)) {
                count = Integer.parseInt(s);
                head = args.shift();
            }
        }

        TransportType transport = null;
        if (head != null) {
            transport = TransportType.parse(head, null);
            if (transport != null)
                head = args.shift();
        }

        int port = 0;
        String service = null;
        if (head != null) {
            if (StringPred.isInteger(head)) {
                port = Integer.parseInt(head);
                head = args.shift();
            } else {
                StdService std = StdService.getService(head);
                if (std != null) {
                    service = std.getName();
                    port = std.getPort();
                    if (transport == null)
                        transport = std.getTransports().iterator().next();
                    head = args.shift();
                }
            }
        }

        if (head == null || !head.equals("over"))
            error("expect keyword over in open statement -> " + head);
        head = args.shift();

        String remoteHost = "localhost";
        int remotePort;
        if (head != null) {
            remoteHost = head;
            head = args.shift();
        }

        if (!StringPred.isInteger(head))
            error("expect remote port number -> " + head);
        assert head != null;
        remotePort = Integer.parseInt(head);

        open(count, transport, service, port, remoteHost, remotePort);
    }

    void close(IArgQueue args)
            throws IOException {
        String head = args.shift();
        if (StringPred.isInteger(head)) {
            int id = Integer.parseInt(head);
            close(id);
            return;
        }
        if (head != null) {
            switch (head) {
                case "all":
                    closeAll();
                    break;
                default:
                    closeByProtocol(head);
            }
            return;
        }
        error("expect id or protocol");
    }

    void status(IArgQueue args) {
        status();
    }

    public void open(int count, TransportType transportType, String service, int port, String remoteHost, int remotePort) {
        if (remoteHost == null)
            remoteHost = "localhost";
        InetSocketAddress destAddr = new InetSocketAddress(remoteHost, remotePort);
//        OpenSession newSession = new OpenSession(channel, destAddr, poller);
//        applier.accept(newSession);
    }

    public void close(int id) {
    }

    public void closeByProtocol(String protocol) {
    }

    public void closeAll() {
    }

    public void status() {

    }

}
