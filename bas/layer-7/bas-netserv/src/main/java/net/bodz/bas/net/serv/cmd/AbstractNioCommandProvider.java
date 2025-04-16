package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractNioCommandProvider
        implements ICommandProvider {

    protected final SocketChannel channel;

    boolean strictMode = true;

    public AbstractNioCommandProvider(@NotNull SocketChannel channel) {
        this.channel = channel;
    }

    protected void println(String message)
            throws IOException {
        channel.write(ByteBuffer.wrap((message + "\n").getBytes()));
    }

    protected boolean error(Throwable e)
            throws IOException {
        return error(e.getMessage());
    }

    protected boolean error(String message)
            throws IOException {
        println("Error: " + message);
        return false;
    }

    protected void illegal(Command cmd)
            throws IOException {
        println("Illegal command: " + cmd.getName());
    }

}
