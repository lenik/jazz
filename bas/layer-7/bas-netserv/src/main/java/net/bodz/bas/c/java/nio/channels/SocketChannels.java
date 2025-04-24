package net.bodz.bas.c.java.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NetworkChannel;
import java.nio.channels.SocketChannel;

import net.bodz.bas.c.java.net.SocketAddresses;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;

public class SocketChannels {

    static final Logger logger = LoggerFactory.getLogger(SocketChannels.class);

    public static final String ARROW = " ⇀ ";

    public static String getLocalAddress(NetworkChannel channel) {
        if (channel == null)
            return "null";
        try {
            return SocketAddresses.human(channel.getLocalAddress());
        } catch (IOException e) {
            return "(error get local address: " + e.getMessage() + ")";
        }
    }

    public static String getRemoteAddress(SocketChannel channel) {
        if (channel == null)
            return "null";
        try {
            return SocketAddresses.human(channel.getRemoteAddress());
        } catch (IOException e) {
            return "(error get remote address: " + e.getMessage() + ")";
        }
    }

    @NotNull
    public static String getConnectionInfo(SocketChannel channel) {
        return getLocalAddress(channel) + " ⇀ " + getRemoteAddress(channel);
    }

    @NotNull
    public static String getConnectionShortInfo(SocketChannel channel) {
        if (channel.socket().isClosed())
            return "closed";
        StringBuilder buf = new StringBuilder();

        SocketAddress localAddress = null;
        try {
            localAddress = channel.getLocalAddress();
        } catch (IOException e) {
            logger.error("error get local port", e);
        }
        if (localAddress instanceof InetSocketAddress) {
            int port = ((InetSocketAddress) localAddress).getPort();
            buf.append(":").append(port);
        } else {
            buf.append("?");
        }

        if (channel.isConnected()) {
            SocketAddress remoteAddress = null;
            try {
                remoteAddress = channel.getRemoteAddress();
            } catch (IOException e) {
                logger.error("error get remove address", e);
            }
            buf.append(ARROW);
            if (remoteAddress instanceof InetSocketAddress) {
                int port = ((InetSocketAddress) remoteAddress).getPort();
                buf.append(":").append(port);
            } else {
                buf.append("?");
            }
        }
        return buf.toString();
    }

    public static long discardReceivedBytes(SocketChannel channel)
            throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;
        while (true) {
            int numBytesRead = channel.read(buf);
            if (numBytesRead == 0)
                break;
            if (numBytesRead == -1)
                break;
            totalBytesRead += numBytesRead;
            buf.reset();
        }
        return totalBytesRead;
    }

}
