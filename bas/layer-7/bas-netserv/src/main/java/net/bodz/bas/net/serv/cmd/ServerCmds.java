package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.cli.Command;
import net.bodz.bas.cli.IArgQueue;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.session.ISocketSession;

public class ServerCmds
        extends AbstractNioCommandProvider {

    @NotNull
    final ISocketPoller poller;

    @NotNull
    final Consumer<ISocketSession> applier;

    public ServerCmds(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull Consumer<ISocketSession> applier) {
        super(channel);
        this.poller = poller;
        this.applier = applier;
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "server":
                server(cmd);
                return true;
        }
        return false;
    }

    void server(IArgQueue args)
            throws IOException {
        if (args.isNoArgument()) {
            error("expect sub command name: listen, list, stop");
            return;
        }
        Command cmd = args.makeCommand();
        switch (cmd.getName()) {
            case "listen":
                listen(cmd);
                break;
            case "list":
                list(cmd);
                break;
            case "stop":
                stop(cmd);
                break;
            default:
                error("invalid server subcommand: " + cmd.getName());
        }
    }

    void listen(IArgQueue args)
            throws IOException {
        // server listen [#PORT=alloc] on connect relay to #1/starter
        String head = args.shift();
        int port = 0;
        if (StringPred.isInteger(head)) {
            port = Integer.parseInt(head);
            head = args.shift();
        }

        if (head != null)
            switch (head) {
                case "on":
                    head = args.shift();
                    if (head != null)
                        switch (head) {
                            case "connect":
                                onConnect(port, args);
                                return;
                            default:
                                error("expect connect -> " + head);
                                return;
                        }
            }
        error("invalid service statement -> " + head + " " + args.toString(" "));
    }

    void onConnect(int port, IArgQueue args)
            throws IOException {
        String head = args.shift();
        if (head == null) {
            error("expect on connect behavior -> " + head);
            return;
        }
        switch (head) {
            case "relay":
                head = args.shift();
                if (head != null)
                    switch (head) {
                        case "to":
                            head = args.shift();
                            if (StringPred.isInteger(head)) {
                                int serviceId = Integer.parseInt(head);
                                onConnectRelayTo(port, serviceId);
                                return;
                            }
                        default:
                            error("expect to -> " + head);
                            return;
                    }
                break;
            default:
                error("invalid behavior: " + head);
                return;
        }
    }

    void list(IArgQueue args) {
        list();
    }


    void stop(IArgQueue args)
            throws IOException {
        String head = args.shift();
        if (StringPred.isInteger(head)) {
            int serverId = Integer.parseInt(head);
            stop(serverId);
            return;
        }
        error("expect server id -> " + head);
    }

    public void onConnectRelayTo(int port, int serviceId) {

    }

    public void list() {

    }

    public void stop(int serverId) {

    }


}
