package net.bodz.bas.net.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import net.bodz.bas.meta.decl.NotNull;

public abstract class Session
        implements ISession {

    protected ISessionManager sessionManager;
    protected SocketChannel channel;

    boolean stopped;

    public Session(@NotNull SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public ISessionManager getManager() {
        return sessionManager;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void stop() {
        stopped = true;
    }

    @Override
    public void onConnect(SocketChannel channel) {

    }

    @Override
    public void onError(SocketChannel channel,Throwable e) {

    }

    @Override
    public void onDisconnect(SocketChannel channel) {

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