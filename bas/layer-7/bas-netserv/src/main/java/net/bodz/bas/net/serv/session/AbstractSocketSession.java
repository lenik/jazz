package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.ISessionManager;

public abstract class AbstractSocketSession
        implements ISocketSession {

    protected final SocketChannel channel;

    boolean closed;

    public AbstractSocketSession(@NotNull SocketChannel channel) {
        this.channel = channel;
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