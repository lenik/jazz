package net.bodz.bas.t.specmap;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.tuple.Split;

public class HostPortSpecMap<T> {

    DomainSpecMap<IntSpecMap<T>> hostPortMap;

    public HostPortSpecMap() {
        hostPortMap = new DomainSpecMap<>();
    }

    public T find(String hostPortSpec) {
        Split split = Split.hostPort(hostPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        return find(split.a, port);
    }

    public T find(String hostName, Integer port) {
        IntSpecMap<T> portMap = hostPortMap.find(hostName);
        if (portMap == null)
            return null;
        if (port != null)
            return portMap.find(port);
        else
            return portMap.getDefault();
    }

    public T getTop(String hostPortSpec) {
        Split split = Split.hostPort(hostPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        return getTop(split.a, port);
    }

    public T getTop(String hostName, Integer port) {
        IntSpecMap<T> portMap = hostPortMap.getTop(hostName);
        if (portMap == null)
            return null;
        if (port != null)
            return null;
        else
            return portMap.getDefault();
    }

    public void putTop(String hostPortSpec, T value) {
        Split split = Split.hostPort(hostPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        putTop(split.a, port, value);
    }

    public void putTop(String hostSpec, Integer port, T value) {
        _putOrAddTop(hostSpec, port, value, true);
    }

    public boolean addTop(String hostPortSpec, T value) {
        Split split = Split.hostPort(hostPortSpec);
        Integer port = null;
        if (split.b != null)
            port = Integer.parseInt(split.b);
        return addTop(split.a, port, value);
    }

    public boolean addTop(String hostSpec, Integer port, T value) {
        return _putOrAddTop(hostSpec, port, value, false);
    }

    boolean _putOrAddTop(String hostSpec, Integer port, T value, boolean overwrite) {
        if (hostSpec == null)
            throw new NullPointerException("hostSpec");

        Split headDomain = Split.headDomain(hostSpec);
        IntSpecMap<T> ports;
        IntSpecMap<T> newPorts = new IntSpecMap<>();
        if (headDomain.a.equals("*")) {
            if (headDomain.b == null)
                ports = hostPortMap.getOrAddDefault(newPorts);
            else
                ports = hostPortMap.getOrAddDomain(headDomain.b, newPorts);
        } else {
            ports = hostPortMap.getOrAddTop(hostSpec, newPorts);
        }

        if (ports == null)
            throw new IllegalUsageException("ports is not nullable.");

        if (overwrite) {
            if (port == null)
                ports.putDefault(value);
            else
                ports.putTop(port, value);
            return true;
        } else {
            if (port == null)
                return ports.addDefault(value);
            else
                return ports.addTop(port, value);
        }
    }

}
