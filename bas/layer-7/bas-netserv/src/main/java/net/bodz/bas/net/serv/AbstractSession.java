package net.bodz.bas.net.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractSession
        implements ISession {

    protected final String id;
    protected final SocketChannel channel;

    boolean closed;

    public AbstractSession(@NotNull String id, @NotNull SocketChannel channel) {
        this.id = id;
        this.channel = channel;
    }

    @Override
    public String getId() {
        return id;
    }

    @NotNull
    @Override
    public SocketChannel getChannel() {
        return channel;
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    protected void error(String message)
            throws IOException {
        error(message, null);
    }

    protected void error(String message, Throwable e)
            throws IOException {
        if (e != null)
            message += "\nStacktrace: ";
        byte[] bytes = message.getBytes();
        channel.write(ByteBuffer.wrap(bytes));

        if (e != null) {
            StringWriter buf = new StringWriter();
            e.printStackTrace(new PrintWriter((buf)));
            byte[] stackTrace = buf.toString().getBytes();
            channel.write(ByteBuffer.wrap(stackTrace));
        }
    }

}