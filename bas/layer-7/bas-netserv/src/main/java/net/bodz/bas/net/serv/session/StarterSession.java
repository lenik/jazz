package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.net.serv.Session;
import net.bodz.bas.parser.CmdQueue;
import net.bodz.bas.parser.Command;

public class StarterSession
        extends Session {

    CmdQueue cmds = new CmdQueue();

    public StarterSession(SocketChannel channel) {
        super(channel);
    }

    @Override
    public void onDataReady(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer aByte = ByteBuffer.allocate(1);
        int numBytesRead = channel.read(aByte);
        if (numBytesRead == -1)                        // The client has closed the connection
        {
            stop();
            return;
        }
        if (numBytesRead == 0)
            return;

        byte b = aByte.get();
        cmds.putOctet(b & 0xFF);

        cmds.parse();

        if (cmds.isNotEmpty()) {
            Iterator<Command> cmdIterator = cmds.iterator();
            while (cmdIterator.hasNext()) {
                Command cmd = cmdIterator.next();
                cmdIterator.remove();
                switch (cmd.getName()) {
                    case "echo":
                        byte[] args = cmd.getArgumentsLine().getBytes();
                        channel.write(ByteBuffer.wrap(args));
                        break;

                    case "relay":
                        // relay PORT [hostname]
                        relay(cmd);
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
        RelaySession relaySession = new RelaySession(channel, destAddr);
        sessionManager.replaceSession(this, relaySession);
    }

}
