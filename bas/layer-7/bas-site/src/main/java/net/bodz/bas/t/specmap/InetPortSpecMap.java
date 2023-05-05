package net.bodz.bas.t.specmap;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.IllegalUsageException;

public class InetPortSpecMap<val_t>
        extends AbstractSpecMap<IInetPort, val_t> {

    NetAddrSpecMap<IntSpecMap<val_t>> ipPortMap;

    InetPortSpecMap(NetAddrSpecMap<IntSpecMap<val_t>> map) {
        ipPortMap = map;
    }

    public static <T> InetPortSpecMap<T> ipv4() {
        return new InetPortSpecMap<>(new IPv4SpecMap<>());
    }

    public static <T> InetPortSpecMap<T> ipv6() {
        return new InetPortSpecMap<>(new IPv6SpecMap<>());
    }

    @Override
    public val_t find(IInetPort ap) {
        IntSpecMap<val_t> portMap = ipPortMap.find(ap.getAddress32());
        if (portMap == null)
            return null;
        int port = ap.getPort();
        if (port != 0)
            return portMap.find(port);
        else
            return portMap.getDefault();
    }

    @Override
    public val_t getTop(IInetPort ap) {
        IntSpecMap<val_t> portMap = ipPortMap.getTop(ap.getAddress32());
        if (portMap == null)
            return null;
        int port = ap.getPort();
        if (port != 0)
            return portMap.getTop(port);
        else
            return null;
    }

    @Override
    public val_t putTop(IInetPort ap, val_t value) {
        return _putOrAddTop(ap, value, true);
    }

    @Override
    public boolean addTop(IInetPort ap, val_t value) {
        return _putOrAddTop(ap, value, false) == null;
    }

    val_t _putOrAddTop(IInetPort ap, val_t value, boolean overwrite) {
        if (ap == null)
            throw new NullPointerException("ap");

        int prefix = ap.getMaskBits();
        IntSpecMap<val_t> ports = null;
        if (prefix != 0) {
            ports = ipPortMap.resolvePrefixNode(ap.getAddress32(), prefix, true).getValue();
        } else {
            ports = ipPortMap.lazyCreateNode(ap.getAddress32()).getValue();
        }

        if (ports == null)
            throw new IllegalUsageException("ports is not nullable.");

        int port = ap.getPort();
        if (overwrite) {
            if (port == 0)
                return ports.putDefault(value);
            else
                return ports.putTop(port, value);
        } else {
            val_t old;
            if (port == 0) {
                old = ports.getDefault();
                ports.addDefault(value);
            } else {
                old = ports.getTop(port);
                ports.addTop(port, value);
            }
            return old;
        }
    }

    @Override
    public boolean hasTop() {
        return !topKeySet().isEmpty();
    }

    @Override
    public Set<IInetPort> topKeySet() {
        Set<IInetPort> hosts = new HashSet<>();
        for (int[] topKey : ipPortMap.topKeySet()) {
            IntSpecMap<val_t> portMap = ipPortMap.getTop(topKey);
            for (Integer port : portMap.topKeySet()) {
                InetPort32 ap = new InetPort32(topKey);
                if (port != null)
                    ap.setPort(port);
                hosts.add(ap);
            }
            if (portMap.hasDefault()) {
                InetPort32 ap = new InetPort32(topKey);
                hosts.add(ap);
            }
        }
        return hosts;
    }

    @Override
    public boolean containsTop(IInetPort key) {
        return topKeySet().contains(key);
    }

    @Override
    public val_t removeTop(IInetPort key) {
        IntSpecMap<val_t> removed = ipPortMap.removeTop(key.getAddress32());
        int port = key.getPort();
        if (port == 0)
            return removed.removeDefault();
        else
            return removed.removeTop(port);
    }

    @Override
    public void removeAllTops() {
        ipPortMap.removeAllTops();
    }

}
