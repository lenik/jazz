package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.object.Nullables;

public class HostSpecifier {

    private String hostName;
    private int port;
    private boolean includeSubDomains;

    public HostSpecifier(String hostName, int port, boolean includeSubDomains) {
        this.hostName = hostName;
        this.port = port;
        this.includeSubDomains = includeSubDomains;
    }

    /**
     * Examples:
     * 
     * <ul>
     * <li>*.example.com:8080</li>
     * </ul>
     */
    public static HostSpecifier parse(String s) {
        if (s == null)
            throw new NullPointerException("s");

        String hostName = null;
        int port = 0;
        boolean includeSubDomains = s.startsWith("*.");
        if (includeSubDomains)
            s = s.substring(2);

        int lastColon = s.lastIndexOf(':');
        if (lastColon != -1) {
            hostName = s.substring(0, lastColon);
            String portStr = s.substring(lastColon + 1);
            if (portStr.equals("*"))
                port = 0;
            else
                port = Integer.parseInt(portStr);
        } else {
            hostName = s;
            port = 80;
        }

        if (hostName.equals("*")) {
            hostName = null;
            includeSubDomains = true;
        } else
            hostName = hostName.toLowerCase();

        return new HostSpecifier(hostName, port, includeSubDomains);
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        if (hostName != null)
            hostName = hostName.toLowerCase();
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isIncludeSubDomains() {
        return includeSubDomains;
    }

    public void setIncludeSubDomains(boolean includeSubDomains) {
        this.includeSubDomains = includeSubDomains;
    }

    public boolean hit(HttpServletRequest request) {
        if (port != 0)
            if (port != request.getServerPort())
                return false;

        if (hostName != null) {
            String name2 = request.getServerName().toLowerCase();
            if (includeSubDomains) {
                if (name2.endsWith(hostName)) {
                    int len = hostName.length();
                    int len2 = name2.length();
                    if (len2 > len) {
                        char prev = name2.charAt(len2 - len - 1);
                        if (prev != '.')
                            return false;
                    }
                } else
                    return false;
            } else {
                if (!name2.equals(hostName))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != HostSpecifier.class)
            return false;
        HostSpecifier o = (HostSpecifier) obj;
        if (port != o.port)
            return false;
        if (includeSubDomains != o.includeSubDomains)
            return false;
        if (!Nullables.equals(hostName, o.hostName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 0x833be381;
        if (hostName != null)
            hash += hostName.hashCode();
        hash = hash * prime + port;
        if (includeSubDomains)
            hash = hash * prime;
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        if (hostName != null) {
            if (includeSubDomains)
                sb.append("*.");
            sb.append(hostName);
        } else
            sb.append("*");

        if (port != 0) {
            sb.append(':');
            sb.append(port);
        }
        return sb.toString();
    }

}
