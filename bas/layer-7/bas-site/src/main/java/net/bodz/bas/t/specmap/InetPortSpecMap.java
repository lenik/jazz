package net.bodz.bas.t.specmap;

import java.util.HashSet;
import java.util.Set;

public class InetPortSpecMap<val_t>
        extends AbstractSpecMapBase<IInetPort, val_t> {

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

        IntSpecNode<IntSpecMap<val_t>> portsNode;
        if (prefix != 0) {
            portsNode = ipPortMap.resolvePrefixNode(ap.getAddress32(), prefix, true);
        } else {
            portsNode = ipPortMap.lazyCreateNode(ap.getAddress32());
        }

        IntSpecMap<val_t> ports = portsNode.getOrAddValue(new IntSpecMap<val_t>());

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

    // Prefixes

    public boolean containsPrefix(IInetPort key) {
        return ipPortMap.containsPrefix(key.getAddress32(), key.getMaskBits());
    }

    public val_t getPrefix(IInetPort key) {
        IntSpecMap<val_t> ports = ipPortMap.getPrefix(key.getAddress32(), key.getMaskBits());
        if (ports == null)
            return null;
        if (key.getPort() == 0)
            return ports.getDefault();
        else
            return ports.getTop(key.getPort());
    }

    IntSpecMap<val_t> lazyCreatePrefix(IInetPort key) {
        IntSpecNode<IntSpecMap<val_t>> node = ipPortMap.lazyCreatePrefixNode(//
                key.getAddress32(), key.getMaskBits());
        IntSpecMap<val_t> ports = node.getValue();
        if (ports == null) {
            IntSpecMap<val_t> newPorts = new IntSpecMap<>();
            node.putValue(newPorts);
            ports = newPorts;
        }
        return ports;
    }

    public val_t putPrefix(IInetPort key, val_t value) {
        IntSpecMap<val_t> ports = lazyCreatePrefix(key);
        if (key.getPort() == 0)
            return ports.putDefault(value);
        else
            return ports.putTop(key.getPort(), value);
    }

    public boolean addPrefix(IInetPort key, val_t value) {
        IntSpecMap<val_t> ports = lazyCreatePrefix(key);
        if (key.getPort() == 0)
            return ports.addDefault(value);
        else
            return ports.addTop(key.getPort(), value);
    }

    public val_t removePrefix(IInetPort key) {
        IntSpecNode<IntSpecMap<val_t>> node = ipPortMap.resolvePrefixNode(//
                key.getAddress32(), key.getMaskBits(), false);
        if (node == null)
            return null;
        IntSpecMap<val_t> ports = node.getValue();
        if (key.getPort() == 0)
            return ports.removeDefault();
        else
            return ports.removeTop(key.getPort());
    }

    public void removeAllPrefixes() {
        ipPortMap.removeAllPrefixes();
        // TODO
    }

}
