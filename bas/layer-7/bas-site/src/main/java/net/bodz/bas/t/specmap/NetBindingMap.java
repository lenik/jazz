package net.bodz.bas.t.specmap;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public class NetBindingMap<val_t> {

    public final HostPortSpecMap<val_t> nameMap = new HostPortSpecMap<>();
    public final InetPortSpecMap<val_t> ipv4Map = InetPortSpecMap.ipv4();
    public final InetPortSpecMap<val_t> ipv6Map = InetPortSpecMap.ipv6();

    public val_t find(String addressPort)
            throws ParseException {
        AddressKind kind = AddressKind.detect(addressPort);
        switch (kind) {
        case DOMAIN_NAME:
            return nameMap.find(addressPort);

        case IPv4:
            InetPort32 ap = InetPort32.parse(addressPort);
            return ipv4Map.find(ap);

        case IPv6:
            InetPort32 ap6 = InetPort32.parse6(addressPort);
            return ipv6Map.find(ap6);

        default:
            throw new UnexpectedException("invalid unknown address kind: " + kind);
        }
    }

    public val_t find(String address, Integer port)
            throws ParseException {
        AddressKind kind = AddressKind.detect(address);
        switch (kind) {
        case DOMAIN_NAME:
            return nameMap.find(address, port);

        case IPv4:
            InetPort32 ap = InetPort32.parse(address, port);
            return ipv4Map.find(ap);

        case IPv6:
            InetPort32 ap6 = InetPort32.parse6(address, port);
            return ipv6Map.find(ap6);

        default:
            throw new UnexpectedException("invalid unknown address kind: " + kind);
        }
    }

    public val_t getTop(String addressPort)
            throws ParseException {
        AddressKind kind = AddressKind.detect(addressPort);
        switch (kind) {
        case DOMAIN_NAME:
            return nameMap.getTop(addressPort);

        case IPv4:
            InetPort32 ap = InetPort32.parse(addressPort);
            return ipv4Map.getTop(ap);

        case IPv6:
            InetPort32 ap6 = InetPort32.parse6(addressPort);
            return ipv6Map.getTop(ap6);

        default:
            throw new UnexpectedException("invalid unknown address kind: " + kind);
        }
    }

    public val_t getTop(String address, Integer port)
            throws ParseException {
        AddressKind kind = AddressKind.detect(address);
        switch (kind) {
        case DOMAIN_NAME:
            return nameMap.getTop(address, port);

        case IPv4:
            InetPort32 ap = InetPort32.parse(address, port);
            return ipv4Map.getTop(ap);

        case IPv6:
            InetPort32 ap6 = InetPort32.parse6(address, port);
            return ipv6Map.getTop(ap6);

        default:
            throw new UnexpectedException("invalid unknown address kind: " + kind);
        }
    }

}

enum AddressKind {
    DOMAIN_NAME,
    IPv4,
    IPv6,

    ;

    /**
     * @param serverName
     *            without port number
     */
    public static AddressKind detect(String serverName) {
        if (serverName.startsWith("[") && serverName.endsWith("]")) {
            // expect ipv6
            serverName = serverName.substring(1, serverName.length() - 1);
            // return ipv6;
        }

        int lastDot = serverName.lastIndexOf('.');
        if (lastDot != -1) {
            String tld = serverName.substring(lastDot + 1);
            if (StringPred.isDecimal(tld)) {
                if (checkIPv4(serverName.substring(0, lastDot), 4))
                    return IPv4;
            }
        }
        int colon = serverName.indexOf(':');
        if (colon != -1)
            if (checkIPv6(serverName, 8))
                return IPv6;
        return DOMAIN_NAME;
    }

    static boolean checkIPv4(String addr, int n) {
        assert n >= 1;
        if (n == 0)
            return true;

        int dot = addr.indexOf('.');
        String item = dot != -1 ? addr.substring(0, dot) : addr;
        if (!StringPred.isDecimal(item))
            return false;

        int num = Integer.parseInt(item);
        if (num < 0 || num >= 256)
            return false;
        if (n == 1)
            return true;

        String remain = addr.substring(dot + 1);
        if (remain.isEmpty())
            return false;
        return checkIPv4(remain, n - 1);
    }

    static boolean checkIPv6(String addr, int n) {
        assert n >= 1;
        if (n == 0)
            return true;

        int colon = addr.indexOf(':');
        String item = colon != -1 ? addr.substring(0, colon) : addr;
        if (!StringPred.isHexadecimal(item))
            return false;

        int num = Integer.parseInt(item, 16);
        if (num < 0 || num >= 65536)
            return false;
        if (n == 1)
            return true;

        String remain = addr.substring(colon + 1);
        if (remain.isEmpty())
            return false;
        return checkIPv6(remain, n - 1);
    }

}