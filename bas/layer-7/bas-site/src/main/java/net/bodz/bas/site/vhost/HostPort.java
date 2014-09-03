package net.bodz.bas.site.vhost;

import java.io.Serializable;

public class HostPort
        implements Serializable, Comparable<HostPort> {

    private static final long serialVersionUID = 1L;

    private final String hostName;
    private final int port;

    public HostPort(String hostName, int port) {
        if (hostName == null)
            throw new NullPointerException("hostName");
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != HostPort.class)
            return false;
        HostPort o = (HostPort) obj;
        if (!hostName.equals(o.hostName))
            return false;
        if (o.port != port)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 1021;
        int hash = 0x0cc14b03;
        hash += hostName.hashCode();
        hash += port * prime;
        return hash;
    }

    @Override
    public int compareTo(HostPort o) {
        if (o == null)
            return 1;
        int cmp = hostName.compareTo(o.hostName);
        if (cmp != 0)
            return cmp;
        cmp = port - o.port;
        if (cmp != 0)
            return cmp;
        return 0;
    }

    @Override
    public String toString() {
        return hostName + ":" + port;
    }

}
