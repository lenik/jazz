package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.cli.CmdQueue;
import net.bodz.bas.cli.Command;
import net.bodz.bas.net.serv.cmd.ICommandProvider;

public abstract class AbstractCmdSession
        extends AbstractSession {

    CmdQueue cmds = new CmdQueue();

    List<ICommandProvider> providers = new ArrayList<>();

    public AbstractCmdSession(String id, SocketChannel channel) {
        super(id, channel);
    }

    @Override
    public boolean read(@NotNull SocketChannel channel)
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
            while (cmds.isNotEmpty()) {
                Command cmd = cmds.take();
                handleCommand(cmd);
            }
        }
    }

    protected void addProvider(@NotNull ICommandProvider provider) {
        providers.add(provider);
    }

    public void handleCommand(Command cmd)
            throws IOException {
        for (ICommandProvider provider : providers) {
            try {
                if (provider.execute(cmd))
                    return;
            } catch (ParseException e) {
                String message = String.format("Error parse command %s from provider %s: %s", //
                        cmd.toString(), provider.toString(), e.getMessage());
                println(message);
            }
        }
        println("unknown command: " + cmd.getName());
    }

    void println(String message)
            throws IOException {
        channel.write(ByteBuffer.wrap((message + "\n").getBytes()));
    }

}
