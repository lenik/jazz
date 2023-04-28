package net.bodz.bas.t.specmap;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.tuple.Split;

public class IPv4PortSpecMap<T> {

    IPv4SpecMap<IntSpecMap<T>> ipPortMap;

    public IPv4PortSpecMap() {
        ipPortMap = new IPv4SpecMap<>();
    }

    public T find(String ipPortSpec)
            throws ParseException {
        Split split = Split.hostPort(ipPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        return find(split.a, port);
    }

    public T find(String ip, Integer port)
            throws ParseException {
        IntSpecMap<T> portMap = ipPortMap.find(ip);
        if (portMap == null)
            return null;
        if (port != null)
            return portMap.find(port);
        else
            return portMap.getDefault();
    }

    public void put(String ipPortSpec, T value) {
        Split split = Split.hostPort(ipPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        put(split.a, port, value);
    }

    public void put(String ipSpec, Integer port, T value) {
        _putOrAdd(ipSpec, port, value, true);
    }

    public boolean add(String ipSpec, Integer port, T value) {
        return _putOrAdd(ipSpec, port, value, false);
    }

    public boolean add(String ipPortSpec, T value) {
        Split split = Split.hostPort(ipPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        return add(split.a, port, value);
    }

    boolean _putOrAdd(String ipSpec, Integer port, T value, boolean overwrite) {
        if (ipSpec == null)
            throw new NullPointerException("ipSpec");

        int slash = ipSpec.indexOf('/');
        String ip = ipSpec;
        int prefix = 0;
        if (slash != -1) {
            ip = ipSpec.substring(0, slash);
            String _prefix = ipSpec.substring(slash + 1);
            prefix = Integer.parseInt(_prefix);
        }

        IntSpecNode<IntSpecMap<T>> term;
        IntSpecMap<T> ports = null;
        IntSpecMap<T> newPorts = new IntSpecMap<>();
        if (prefix != 0) {
            term = ipPortMap.getOrAddPrefixNode(ip, prefix, true);
            assert term != null; // empty ..?
            ports = term.getValue();
        } else {
            ipPortMap.getOrAddPrefixNode(ip, prefix, true);
        }

        if (ports == null)
            throw new IllegalUsageException("ports is not nullable.");

        if (overwrite) {
            if (port == null)
                ports.putDefault(value);
            else
                ports.put(port, value);
            return true;
        } else {
            if (port == null)
                return ports.addDefault(value);
            else
                return ports.add(port, value);
        }
    }

}
