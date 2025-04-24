package net.bodz.bas.c.java.net;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import net.bodz.bas.meta.decl.NotNull;

public class SocketAddresses {

    @NotNull
    public static String human(SocketAddress addr) {
        if (addr == null)
            return "null";
        if (addr instanceof InetSocketAddress)
            return human((InetSocketAddress) addr);
        return addr.toString();
    }

    @NotNull
    public static String human(InetSocketAddress addr) {
        if (addr == null)
            return "null";
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

}
