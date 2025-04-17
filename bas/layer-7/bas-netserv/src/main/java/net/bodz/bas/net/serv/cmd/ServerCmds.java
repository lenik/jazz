package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;

public class ServerCmds
        extends AbstractNioCommandProvider {

    public ServerCmds(SocketChannel channel) {
        super(channel);
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "server":
                String name = cmd.shift();
                if (name == null) {
                    error("expect sub command name: listen, list, stop");
                    return true;
                }
                Command subCmd = new Command();
                subCmd.setName(name);
                subCmd.setArguments(cmd.getArguments());
                server(cmd);
                return true;
        }
        return false;
    }

    void server(Command cmd)
            throws IOException {
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

    void listen(Command cmd)
            throws IOException {
        // server listen [#PORT=alloc] on connect relay to #1/starter
        String head = cmd.shift();
        int port = 0;
        if (StringPred.isInteger(head)) {
            port = Integer.parseInt(head);
            head = cmd.shift();
        }

        if (head != null)
            switch (head) {
                case "on":
                    head = cmd.shift();
                    if (head != null)
                        switch (head) {
                            case "connect":
                                onConnect(port, cmd);
                                return;
                            default:
                                error("expect connect -> " + head);
                                return;
                        }
            }
        error("invalid service statement -> " + head + " " + cmd.getArgumentsLine());
    }

    void onConnect(int port, Command cmd)
            throws IOException {
        String head = cmd.shift();
        if (head == null) {
            error("expect on connect behavior -> " + head);
            return;
        }
        switch (head) {
            case "relay":
                head = cmd.shift();
                if (head != null)
                    switch (head) {
                        case "to":
                            head = cmd.shift();
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

    void list(Command cmd) {
        list();
    }


    void stop(Command cmd)
            throws IOException {
        String head = cmd.shift();
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
