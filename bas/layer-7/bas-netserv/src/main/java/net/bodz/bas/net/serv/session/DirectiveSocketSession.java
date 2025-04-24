package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.cli.CmdQueue;
import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.cmd.CommonCmds;
import net.bodz.bas.net.serv.cmd.ICommandProvider;

public abstract class DirectiveSocketSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(DirectiveSocketSession.class);

    CmdQueue cmds = new CmdQueue();
    List<ICommandProvider> providers = new ArrayList<>();

    public DirectiveSocketSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller) {
        super(name, channel, poller);

        CommonCmds commons = new CommonCmds(channel);
        commons.addSettingSource(this);
        addProvider(commons);
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        long totalBytesRead = 0;
        ByteBuffer aByte = ByteBuffer.allocate(1);

        while (true) {
            int numBytesRead = channel.read(aByte);
            switch (numBytesRead) {
                case -1:            // The client has closed the connection
                    close();
                case 0:
                    return totalBytesRead;

                case 1:
                    totalBytesRead += numBytesRead;

                    aByte.flip();
                    byte b = aByte.get();
                    aByte.clear();
                    cmds.putOctet(b);
                    break;

                default:
                    throw new UnexpectedException();
            }

            try {
                cmds.parse();
            } catch (ParseException e) {
                logger.error(e, "error parse: " + e.getMessage());
            }

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
                buffer.printError(message);
                return;
            }
        }
        buffer.printError("unknown command: " + cmd.getName());
    }

}
