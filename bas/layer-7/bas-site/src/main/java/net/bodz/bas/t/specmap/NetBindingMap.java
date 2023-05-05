package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ExceptionSuppliers;
import net.bodz.bas.err.ParseException;

public class NetBindingMap<val_t>
        extends AbstractSpecMap<String, val_t> {

    public final HostSpecMap<val_t> nameMap = new HostSpecMap<>();
    public final InetPortSpecMap<val_t> ipv4Map = InetPortSpecMap.ipv4();
    public final InetPortSpecMap<val_t> ipv6Map = InetPortSpecMap.ipv6();

    @Override
    public val_t find(String host) {
        Endpoint endpoint = Endpoint.parse(host);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.find(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.find(endpoint.toInetPort());

        case IPv4_MASK:
            ipv4Map.ipPortMap.getPrefix(host, prefix); // TODO ....
            return null;

        case DOMAIN_NAME:
            return nameMap.find(host);

        default:
            throw endpoint.errorType();
        }
    }

    public val_t find(String hostName, int port)
            throws ParseException {
        Endpoint endpoint = Endpoint.parse(hostName, port);
        switch (endpoint.type) {
        case IPv4_MASK:
        case IPv4:
            return ipv4Map.find(endpoint.toInetPort());

        case IPv6_MASK:
        case IPv6:
            return ipv6Map.find(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.find(hostName, port);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public val_t getTop(String host) {
        Endpoint endpoint = Endpoint.parse(host, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.getTop(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.getTop(host);

        default:
            throw endpoint.errorType();
        }
    }

    public val_t getTop(String hostName, int port)
            throws ParseException {
        Endpoint endpoint = Endpoint.parse(hostName, port);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.getTop(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.getTop(hostName, port);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public val_t putTop(String host, val_t value) {
        Endpoint endpoint = Endpoint.parse(host, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.putTop(endpoint.toInetPort(), value);

        case IPv4_MASK:
            return ipv4Map.putTop(endpoint.toInetPort(), value);

        case IPv6:
            return ipv6Map.putTop(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.putTop(host, value);

        default:
            throw endpoint.errorType();
        }
    }

    public void putTop(String hostName, Integer port, val_t value)
            throws ParseException {
        Endpoint endpoint = Endpoint.parse(hostName, port);
        switch (endpoint.type) {
        case IPv4:
            ipv4Map.putTop(endpoint.toInetPort(), value);
            return;

        case IPv6:
            ipv6Map.putTop(endpoint.toInetPort(), value);
            return;

        case DOMAIN_NAME:
            nameMap.putTop(hostName, port, value);
            return;

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public boolean addTop(String host, val_t value) {
        Endpoint endpoint = Endpoint.parse(host, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.addTop(endpoint.toInetPort(), value);

        case IPv6:
            return ipv6Map.addTop(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.addTop(host, value);

        default:
            throw endpoint.errorType();
        }
    }

    public boolean addTop(String hostName, Integer port, val_t value) {
        Endpoint endpoint = Endpoint.parse(hostName, port, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.addTop(endpoint.toInetPort(), value);

        case IPv6:
            return ipv6Map.addTop(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.addTop(hostName, port, value);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("domains: \n" + nameMap + "\n");
        sb.append("ipv4: \n" + ipv4Map + "\n");
        sb.append("ipv6: \n" + ipv6Map);
        return sb.toString();
    }

}
