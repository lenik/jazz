package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISettingParsable;
import net.bodz.bas.repr.path.IBasicTokenQueue;
import net.bodz.bas.t.variant.IVariant;

public abstract class AbstractSocketSession
        implements ISocketSession,
                   ISettingParsable {

    protected final SocketChannel channel;
    protected final ISocketPoller poller;

    boolean closed;

    String linePrefix;
    String lineSuffix;
    String eol = "\n";
    Charset charset = StandardCharsets.UTF_8;

    public AbstractSocketSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller) {
        this.channel = channel;
        this.poller = poller;
    }

    //    @Override
    protected String getTitle() {
        StringBuilder buf = new StringBuilder(100);

        String type = getClass().getSimpleName();
        if (type.endsWith("Session"))
            type = type.substring(0, type.length() - 7);
        buf.append(type);

        if (channel.socket().isClosed())
            buf.append(":closed");
        else {
            String port;
            try {
                InetSocketAddress localAddress = (InetSocketAddress) channel.getLocalAddress();
                int portNum = localAddress.getPort();
                port = String.valueOf(portNum);
            } catch (IOException e) {
                port = "error get local port";
            }

            buf.append(":").append(port);
        }
        return buf.toString();
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
        byte[] bytes = (message + "\n").getBytes(charset);
        channel.write(ByteBuffer.wrap(bytes));

        if (e != null) {
            StringWriter buf = new StringWriter();
            buf.write("Stacktrace:\n");
            e.printStackTrace(new PrintWriter((buf)));
            byte[] stackTrace = buf.toString().getBytes(charset);
            channel.write(ByteBuffer.wrap(stackTrace));
        }
    }

    protected void println(String s)
            throws IOException {
        StringBuilder buf = new StringBuilder(s.length() + 32);
        if (linePrefix != null)
            buf.append(linePrefix);
        buf.append(s);
        if (lineSuffix != null)
            buf.append(lineSuffix);
        if (eol != null)
            buf.append(eol);
        byte[] bin = buf.toString().getBytes(charset);
        channel.write(ByteBuffer.wrap(bin));
    }

    @NotNull
    @Override
    public Set<String> getSettingNames() {
        return new LinkedHashSet<String>(Arrays.asList("prefix", "suffix"));
    }

    @Override
    public Object getSetting(@NotNull String name) {
        switch (name) {
            case "prefix":
                return linePrefix;
            case "suffix":
                return lineSuffix;
        }
        return null;
    }

    @Override
    public boolean setSettingVar(@NotNull String name, @NotNull IVariant var) {
        switch (name) {
            case "prefix":
                linePrefix = var.getString();
                return true;
            case "suffix":
                lineSuffix = var.getString();
                return true;
        }
        return false;
    }

    @Override
    public Object parseSetting(@NotNull String name, @NotNull IBasicTokenQueue args)
            throws ParseException {
        switch (name) {
            case "prefix":
            case "suffix":
                return args.peek();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(100);

        String type = getClass().getSimpleName();
        if (type.endsWith("Session"))
            type = type.substring(0, type.length() - 7);
        buf.append(type);

        String local = SocketChannels.getLocalAddress(channel);
        String remote = SocketChannels.getRemoteAddress(channel);
        buf.append(local);
        buf.append(" -> ").append(remote);

        return buf.toString();
    }

}