package net.bodz.bas.t.specmap;

import net.bodz.bas.err.IllegalUsageException;

public class InetPortSpecMap<T> {

    // IPv4SpecMap<IntSpecMap<T>> ipPortMap;
    NetAddrSpecMap<IntSpecMap<T>> ipPortMap;

    InetPortSpecMap(NetAddrSpecMap<IntSpecMap<T>> map) {
        ipPortMap = map;
    }

    public static <T> InetPortSpecMap<T> ipv4() {
        return new InetPortSpecMap<>(new IPv4SpecMap<>());
    }

    public static <T> InetPortSpecMap<T> ipv6() {
        return new InetPortSpecMap<>(new IPv6SpecMap<>());
    }

    public T find(IInetPort ap) {
        IntSpecMap<T> portMap = ipPortMap.find(ap.getAddress32());
        if (portMap == null)
            return null;
        int port = ap.getPort();
        if (port != 0)
            return portMap.find(port);
        else
            return portMap.getDefault();
    }

    public T getTop(IInetPort ap) {
        IntSpecMap<T> portMap = ipPortMap.getTop(ap.getAddress32());
        if (portMap == null)
            return null;
        int port = ap.getPort();
        if (port != 0)
            return portMap.getTop(port);
        else
            return null;
    }

    public void putTop(IInetPort ap, T value) {
        _putOrAddTop(ap, value, true);
    }

    public boolean addTop(IInetPort ap, T value) {
        return _putOrAddTop(ap, value, false);
    }

    boolean _putOrAddTop(IInetPort ap, T value, boolean overwrite) {
        if (ap == null)
            throw new NullPointerException("ap");

        int prefix = ap.getMaskBits();
        IntSpecNode<IntSpecMap<T>> term;
        IntSpecMap<T> ports = null;
        IntSpecMap<T> newPorts = new IntSpecMap<>();
        if (prefix != 0) {
            term = ipPortMap.resolvePrefixNode(ap.getAddress32(), prefix, true);
            assert term != null; // empty ..?
            ports = term.getValue();
        } else {
            ipPortMap.getOrAddTop(ap.getAddress32(), newPorts);
        }

        if (ports == null)
            throw new IllegalUsageException("ports is not nullable.");

        int port = ap.getPort();
        if (overwrite) {
            if (port == 0)
                ports.putDefault(value);
            else
                ports.putTop(port, value);
            return true;
        } else {
            if (port == 0)
                return ports.addDefault(value);
            else
                return ports.addTop(port, value);
        }
    }

}
