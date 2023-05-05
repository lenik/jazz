package net.bodz.bas.t.specmap;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;

public class EndpointRange
        extends Endpoint {

    int maskBits;
    int portMax;

    /**
     * @param host
     *            port number
     * @throws ParseException
     *             Invalid IPv4 or IPv6 address.
     */
    public static EndpointRange parse(String host)
            throws ParseException {
        String portStr = null;
        int lastColon = host.lastIndexOf(':');
        int v6end = host.indexOf(']');
        if (v6end != -1 && host.startsWith("[")) { // expect ipv6
            String other = host.substring(v6end + 1);
            if (!other.isEmpty())
                if (other.charAt(0) == ':')
                    portStr = other.substring(1);
                else
                    throw new ParseException("invalid ipv6 port specification: " + host);
            host = host.substring(1, host.length() - 1);
        } else if (lastColon != -1) {
            portStr = host.substring(lastColon + 1);
            host = host.substring(0, lastColon);
        }
        return parse(host, portStr);
    }

    public static EndpointRange parse(String hostNameRange, String portRange)
            throws ParseException {
        EndpointRange ans = new EndpointRange();

        if (portRange != null) {
            String portMaxStr = null;
            int dash = portRange.indexOf('-');
            if (dash != -1) {
                portMaxStr = portRange.substring(dash + 1);
                portRange = portRange.substring(0, dash);
            }
            if (!StringPred.isDecimal(portRange))
                throw new IllegalArgumentException("Invalid port number: " + portRange);
            else
                ans.port = Integer.parseInt(portRange);

            if (portMaxStr != null) {
                if (!StringPred.isDecimal(portMaxStr))
                    throw new IllegalArgumentException("Invalid max-port: " + portMaxStr);
                else
                    ans.portMax = Integer.parseInt(portMaxStr);
            }
        }

        String serverName = hostNameRange;
        boolean hasBits = false;
        int slash = hostNameRange.lastIndexOf('/');
        if (slash != -1) {
            String param = hostNameRange.substring(slash + 1);
            if (StringPred.isDecimal(param)) {
                ans.maskBits = Integer.parseInt(param);
                hasBits = true;
                serverName = hostNameRange.substring(0, slash);
            }
        }

        int lastDot = serverName.lastIndexOf('.');
        if (lastDot != -1) {
            String tld = serverName.substring(lastDot + 1);
            if (StringPred.isDecimal(tld)) {
                if (IPv4Address.isValid(serverName)) {
                    ans.type = hasBits ? EndpointType.IPv4_MASK : EndpointType.IPv4;
                    ans.address = IPv4Address.parse(serverName);
                    return ans;
                }
            }
        }

        int colon = serverName.indexOf(':');
        if (colon != -1) {
            if (IPv6Address.isValid(serverName)) {
                ans.type = hasBits ? EndpointType.IPv6_MASK : EndpointType.IPv6;
                ans.address = IPv6Address.parse(serverName);
                return ans;
            }
        }

        if (serverName.startsWith("*.")) {
            ans.type = EndpointType.DOMAIN_NAME_GLOB;
            ans.name = serverName.substring(2);
        } else {
            ans.type = EndpointType.DOMAIN_NAME;
            ans.name = serverName;
        }
        return ans;
    }

}
