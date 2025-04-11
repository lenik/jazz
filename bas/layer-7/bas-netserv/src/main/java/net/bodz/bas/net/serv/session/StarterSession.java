package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.AbstractSession;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.parser.CmdQueue;
import net.bodz.bas.parser.Command;

public class StarterSession
        extends AbstractSession {

    @NotNull
    ISessionManager sessionManager;

    @NotNull
    ISocketPoller poller;

    CmdQueue cmds = new CmdQueue();

    public StarterSession(String id, SocketChannel channel, @NotNull ISessionManager sessionManager, @NotNull ISocketPoller poller) {
        super(id, channel);
        this.sessionManager = sessionManager;
        this.poller = poller;
    }

    @Override
    public boolean read(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer aByte = ByteBuffer.allocate(1);

        while (true) {
            int numBytesRead = channel.read(aByte);
            switch (numBytesRead) {
                case -1:            // The client has closed the connection
                    close();
                case 0:
                    return true;

                case 1:
                    aByte.flip();
                    byte b = aByte.get();
                    aByte.clear();
                    cmds.putOctet(b);
                    break;

                default:
                    throw new UnexpectedException();
            }

            cmds.parse();
            if (cmds.isNotEmpty()) {
                Iterator<Command> cmdIterator = cmds.iterator();
                while (cmdIterator.hasNext()) {
                    Command cmd = cmdIterator.next();
                    cmdIterator.remove();
                    switch (cmd.getName()) {
                        case "echo":
                            echo(cmd);
                            break;

                        case "relay":
                            relay(cmd);
                            break;

                        default:
                            illegal(cmd);
                    }
                }
            }
        }
    }

    static final byte[] newLine = { '\n' };

    void echo(Command cmd)
            throws IOException {
        byte[] args = cmd.getArgumentsLine().getBytes();
        channel.write(ByteBuffer.wrap(args));
        channel.write(ByteBuffer.wrap(newLine));
    }

    void relay(Command cmd)
            throws IOException {
        if (cmd.isNoArgument()) {
            error("Expect relay port.");
            return;
        }

        String portStr = cmd.getArgument(0);
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            error("Invalid port number: " + portStr);
            return;
        }

        String host = "localhost";
        if (cmd.isArgumentPresent(1))
            host = cmd.getArgument(1);
        InetSocketAddress destAddr = new InetSocketAddress(host, port);
        RelaySession relaySession = new RelaySession(id, channel, destAddr, poller);
        sessionManager.replaceSession(this, relaySession);
    }

    void illegal(Command cmd)
            throws IOException {
        String message = "Illegal command; " + cmd.getName() + "\n";
        channel.write(ByteBuffer.wrap(message.getBytes()));
    }

}
