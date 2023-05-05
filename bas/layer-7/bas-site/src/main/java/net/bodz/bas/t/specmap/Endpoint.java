package net.bodz.bas.t.specmap;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ExceptionSupplier;
import net.bodz.bas.err.ParseException;

public class Endpoint {

    EndpointType type;

    String name;
    int[] address;
    int port;

    public IllegalArgumentException errorType() {
        return new IllegalArgumentException("Invalid endpoint type: " + type);
    }

    public IInetPort toInetPort() {
        return new InetPort32(address);
    }

    public InetPort32 toInetPort32() {
        return new InetPort32(address);
    }

    public static Endpoint parse(String host)
            throws ParseException {
        return parse(host, ParseException.SUPPLIER);
    }

    /**
     * @param host
     *            port number
     * @throws E
     *             Invalid IPv4 or IPv6 address.
     */
    public static <E extends Throwable> Endpoint parse(String host, //
            ExceptionSupplier<E> exceptionSupplier)
            throws E {
        String portStr = null;
        int lastColon = host.lastIndexOf(':');
        int v6end = host.indexOf(']');
        if (v6end != -1 && host.startsWith("[")) { // expect ipv6
            String other = host.substring(v6end + 1);
            if (!other.isEmpty())
                if (other.charAt(0) == ':')
                    portStr = other.substring(1);
                else
                    throw exceptionSupplier.supply("invalid ipv6 port specification: " + host);
            host = host.substring(1, host.length() - 1);
        } else if (lastColon != -1) {
            portStr = host.substring(lastColon + 1);
            host = host.substring(0, lastColon);
        }

        int port = 0;
        if (portStr != null) {
            if (!StringPred.isDecimal(portStr))
                throw exceptionSupplier.supply("Invalid port number: " + portStr);
            else
                port = Integer.parseInt(portStr);
        }
        return parse(host, port, exceptionSupplier);
    }

    public static Endpoint parse(String hostName, int port)
            throws ParseException {
        return parse(hostName, port, ParseException.SUPPLIER);
    }

    public static <E extends Throwable> Endpoint parse(String hostName, int port, //
            ExceptionSupplier<E> exceptionSupplier)
            throws E {
        Endpoint ans = new Endpoint();
        ans.port = port;

        String serverName = hostName;
        int lastDot = serverName.lastIndexOf('.');
        if (lastDot != -1) {
            String tld = serverName.substring(lastDot + 1);
            if (StringPred.isDecimal(tld)) {
                if (IPv4Address.isValid(serverName)) {
                    ans.type = EndpointType.IPv4;
                    try {
                        ans.address = IPv4Address.parse(serverName);
                    } catch (ParseException e) {
                        throw exceptionSupplier.supply(e.getMessage(), e);
                    }
                    return ans;
                }
            }
        }

        int colon = serverName.indexOf(':');
        if (colon != -1) {
            if (IPv6Address.isValid(serverName)) {
                ans.type = EndpointType.IPv6;
                try {
                    ans.address = IPv6Address.parse(serverName);
                } catch (ParseException e) {
                    throw exceptionSupplier.supply(e.getMessage(), e);
                }
                return ans;
            }
        }

        ans.type = EndpointType.DOMAIN_NAME;
        ans.name = serverName;
        return ans;
    }

}
