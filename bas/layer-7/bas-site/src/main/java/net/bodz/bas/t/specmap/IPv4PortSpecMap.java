package net.bodz.bas.t.specmap;

import net.bodz.bas.err.IllegalUsageException;

public class IPv4PortSpecMap<T> {

    IPv4SpecMap<IntSpecMap<T>> ipPortMap;

    public IPv4PortSpecMap() {
        ipPortMap = new IPv4SpecMap<>();
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

    public void put(IInetPort ap, T value) {
        _putOrAdd(ap, value, true);
    }

    public boolean add(IInetPort ap, T value) {
        return _putOrAdd(ap, value, false);
    }

    boolean _putOrAdd(IInetPort ap, T value, boolean overwrite) {
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
