package net.bodz.bas.c.java.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannels {

    public static String simpleAddress(SocketAddress addr) {
        if (addr == null)
            return "null";
        if (addr instanceof InetSocketAddress)
            return simpleAddress((InetSocketAddress) addr);
        return addr.toString();
    }

    public static String simpleAddress(InetSocketAddress addr) {
        String host = addr.getHostName();
        if (host != null) {
            if ("localhost".equals(host))
                host = null;
        } else {
            host = addr.getHostString();
            if (host != null) {
                switch (host) {
                    case "127.0.0.1":
                    case "::1":
                        host = null;
                }
            }
        }
        StringBuilder buf = new StringBuilder(40);
        if (host != null)
            buf.append(host);
        buf.append(":").append(addr.getPort());
        return buf.toString();
    }

    public static String getLocalAddress(SocketChannel channel) {
        if (channel == null)
            return "null";
        try {
            return simpleAddress(channel.getLocalAddress());
        } catch (IOException e) {
            return "(error get local address: " + e.getMessage() + ")";
        }
    }

    public static String getRemoteAddress(SocketChannel channel) {
        if (channel == null)
            return "null";
        try {
            return simpleAddress(channel.getRemoteAddress());
        } catch (IOException e) {
            return "(error get remote address: " + e.getMessage() + ")";
        }
    }

    public static String addressInfo(SocketChannel channel) {
        return getLocalAddress(channel) + " => " + getRemoteAddress(channel);
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
