package net.bodz.bas.t.specmap;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.err.ExceptionSuppliers;
import net.bodz.bas.err.ParseException;

public class NetSpecMap<val_t>
        extends AbstractSpecMapBase<String, val_t> {

    public final HostSpecMap<val_t> nameMap = new HostSpecMap<>();
    public final InetPortSpecMap<val_t> ipv4Map = InetPortSpecMap.ipv4();
    public final InetPortSpecMap<val_t> ipv6Map = InetPortSpecMap.ipv6();

    @Override
    public val_t find(String host) {
        EndpointRange endpoint = EndpointRange.parse(host, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.find(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.find(endpoint.toInetPort());

        case IPv4_MASK:
            return ipv4Map.getPrefix(endpoint.toInetPort());

        case IPv6_MASK:
            return ipv6Map.getPrefix(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.find(host);

        default:
            throw endpoint.errorType();
        }
    }

    public val_t find(String hostNameRange, String portRange) {
        EndpointRange endpoint = EndpointRange.parse(hostNameRange, portRange, //
                ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.find(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.find(endpoint.toInetPort());

        case IPv4_MASK:
            return ipv4Map.getPrefix(endpoint.toInetPort());

        case IPv6_MASK:
            return ipv6Map.getPrefix(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.find(endpoint.name, endpoint.port);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public boolean hasTop() {
        return nameMap.hasTop() || ipv4Map.hasTop() || ipv6Map.hasTop();
    }

    @Override
    public Set<String> topKeySet() {
        Set<String> all = new LinkedHashSet<>();
        all.addAll(nameMap.topKeySet());
        for (IInetPort ap : ipv4Map.topKeySet())
            all.add(ap.toString());
        for (IInetPort ap : ipv6Map.topKeySet())
            all.add(ap.toString());
        return all;
    }

    @Override
    public boolean containsTop(String key) {
        if (nameMap.containsTop(key))
            return true;

        InetPort32 ap;
        try {
            ap = InetPort32.parse(key);
        } catch (ParseException e) {
            return false;
        }
        if (ipv4Map.containsTop(ap))
            return true;
        if (ipv6Map.containsTop(ap))
            return true;
        return false;
    }

    @Override
    public val_t getTop(String host) {
        EndpointRange endpoint = EndpointRange.parse(host, //
                ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.getTop(endpoint.toInetPort());

        case IPv4_MASK:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6_MASK:
            return ipv6Map.getTop(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.getTop(host);

        default:
            throw endpoint.errorType();
        }
    }

    public val_t getTop(String hostNameRange, String portRange)
            throws ParseException {
        EndpointRange endpoint = EndpointRange.parse(hostNameRange, portRange, //
                ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6:
            return ipv6Map.getTop(endpoint.toInetPort());

        case IPv4_MASK:
            return ipv4Map.getTop(endpoint.toInetPort());

        case IPv6_MASK:
            return ipv6Map.getTop(endpoint.toInetPort());

        case DOMAIN_NAME:
            return nameMap.getTop(endpoint.name, endpoint.port);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public val_t putTop(String host, val_t value) {
        EndpointRange endpoint = EndpointRange.parse(host, ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.putTop(endpoint.toInetPort(), value);

        case IPv6:
            return ipv6Map.putTop(endpoint.toInetPort(), value);

        case IPv4_MASK:
            return ipv4Map.putPrefix(endpoint.toInetPort(), value);

        case IPv6_MASK:
            return ipv6Map.putPrefix(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.putTop(host, value);

        default:
            throw endpoint.errorType();
        }
    }

    public void putTop(String hostNameRange, String portRange, val_t value)
            throws ParseException {
        EndpointRange endpoint = EndpointRange.parse(hostNameRange, portRange, //
                ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            ipv4Map.putTop(endpoint.toInetPort(), value);
            return;

        case IPv6:
            ipv6Map.putTop(endpoint.toInetPort(), value);
            return;

        case IPv4_MASK:
            ipv4Map.putTop(endpoint.toInetPort(), value);
            return;

        case IPv6_MASK:
            ipv6Map.putTop(endpoint.toInetPort(), value);
            return;

        case DOMAIN_NAME:
            nameMap.putTop(endpoint.name, endpoint.port, value);
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

        case IPv4_MASK:
            return ipv4Map.addTop(endpoint.toInetPort(), value);

        case IPv6_MASK:
            return ipv6Map.addTop(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.addTop(host, value);

        default:
            throw endpoint.errorType();
        }
    }

    public boolean addTop(String hostNameRange, String portRange, val_t value) {
        EndpointRange endpoint = EndpointRange.parse(hostNameRange, portRange, //
                ExceptionSuppliers.illegalArgument);
        switch (endpoint.type) {
        case IPv4:
            return ipv4Map.addTop(endpoint.toInetPort(), value);

        case IPv6:
            return ipv6Map.addTop(endpoint.toInetPort(), value);

        case IPv4_MASK:
            return ipv4Map.addTop(endpoint.toInetPort(), value);

        case IPv6_MASK:
            return ipv6Map.addTop(endpoint.toInetPort(), value);

        case DOMAIN_NAME:
            return nameMap.addTop(endpoint.name, endpoint.port, value);

        default:
            throw endpoint.errorType();
        }
    }

    @Override
    public val_t removeTop(String key) {
        val_t old;
        if ((old = nameMap.removeTop(key)) != null)
            return old;

        IInetPort ap;
        try {
            ap = InetPort32.parse(key);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        if ((old = ipv4Map.removeTop(ap)) != null)
            return old;
        if ((old = ipv6Map.removeTop(ap)) != null)
            return old;
        return null;
    }

    @Override
    public void removeAllTops() {
        nameMap.removeAllTops();
        ipv4Map.removeAllTops();
        ipv6Map.removeAllTops();
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
