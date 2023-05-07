package net.bodz.bas.t.specmap;

import java.util.Set;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.tuple.Split;

public class HostSpecMap<val_t>
        extends AbstractSpecMapBase<String, val_t> {

    DomainSpecMap<IntSpecMap<val_t>> namePortMap;

    public HostSpecMap() {
        namePortMap = new DomainSpecMap<>();
    }

    @Override
    public val_t find(String host) {
        NamePort np = NamePort.parse(host, -1);
        return find(np.name, np.port);
    }

    public val_t find(String name, Integer port) {
        IntSpecMap<val_t> portMap = namePortMap.find(name);
        if (portMap == null)
            return getDefault();

        if (port != null)
            return portMap.find(port);
        else
            return portMap.getDefault();
    }

    @Override
    public Set<String> topKeySet() {
        return namePortMap.topKeySet();
    }

    @Override
    public boolean hasTop() {
        return namePortMap.hasTop();
    }

    @Override
    public boolean containsTop(String host) {
        NamePort np = NamePort.parse(host, -1);
        return containsTop(np.name, np.port);
    }

    public boolean containsTop(String name, Integer port) {
        IntSpecMap<val_t> portMap = namePortMap.getTop(name);
        if (portMap == null)
            return false;

        if (port != null)
            return portMap.containsTop(port);
        else
            return portMap.hasDefault();
    }

    @Override
    public val_t getTop(String host) {
        NamePort np = NamePort.parse(host, -1);
        return getTop(np.name, np.port);
    }

    public val_t getTop(String name, Integer port) {
        IntSpecMap<val_t> portMap = namePortMap.getTop(name);
        if (portMap == null)
            return null;

        if (port != null)
            return portMap.getTop(port);
        else
            return portMap.getDefault();
    }

    @Override
    public val_t putTop(String host, val_t value) {
        NamePort np = NamePort.parse(host, -1);
        return putTop(np.name, np.port, value);
    }

    public val_t putTop(String name, Integer port, val_t value) {
        IntSpecMap<val_t> portMap = namePortMap.getOrAddTop(name, new IntSpecMap<>());
        if (port != null)
            return portMap.putTop(port, value);
        else
            return portMap.putDefault(value);
    }

    @Override
    public boolean addTop(String host, val_t value) {
        NamePort np = NamePort.parse(host, -1);
        return addTop(np.name, np.port, value);
    }

    public boolean addTop(String name, Integer port, val_t value) {
        IntSpecMap<val_t> portMap = namePortMap.getOrAddTop(name, new IntSpecMap<val_t>());
        if (port != null)
            return portMap.addTop(port, value);
        else
            return portMap.addDefault(value);
    }

    @Override
    public val_t removeTop(String host) {
        NamePort np = NamePort.parse(host, -1);
        return removeTop(np.name, np.port);
    }

    public val_t removeTop(String name, Integer port) {
        IntSpecMap<val_t> portMap = namePortMap.getTop(name);
        if (portMap == null)
            return null;
        if (port != null)
            return portMap.removeTop(port);
        else
            return portMap.removeDefault();
    }

    @Override
    public void removeAllTops() {
        namePortMap.removeAllTops();
    }

    public void putPattern(String pattern, val_t value) {
        NamePort np = NamePort.parse(pattern, -1);
        putPattern(np.name, np.port, value);
    }

    public void putPattern(String pattern, Integer port, val_t value) {
        _putOrAddPattern(pattern, port, value, true);
    }

    public boolean addPattern(String pattern, val_t value) {
        NamePort np = NamePort.parse(pattern, -1);
        return addPattern(np.name, np.port, value);
    }

    public boolean addPattern(String pattern, Integer port, val_t value) {
        return _putOrAddPattern(pattern, port, value, false);
    }

    boolean _putOrAddPattern(String pattern, Integer port, val_t value, boolean overwrite) {
        if (pattern == null)
            throw new NullPointerException("pattern");

        Split split = Split.headDomain(pattern);
        String head = split.a;
        String parent = split.b;

        IntSpecMap<val_t> ports;
        IntSpecMap<val_t> newPorts = new IntSpecMap<>();
        if (head.equals("*")) {
            if (parent == null)
                ports = namePortMap.getOrAddDefault(newPorts);
            else
                ports = namePortMap.getOrAddDomain(parent, newPorts);
        } else {
            ports = namePortMap.getOrAddTop(pattern, newPorts);
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

    @Override
    public String toString() {
        return namePortMap.toString();
    }

    @Override
    protected void _accept(ISpecMapVisitor<? super String, ? super val_t> visitor) {
        ISpecMapVisitor<Integer, val_t> visitor_conv = new StringKey2IntVisitor<>(visitor);
        namePortMap.accept(new ISpecMapVisitor<String, IntSpecMap<val_t>>() {

            @Override
            public boolean beginTops() {
                return visitor.beginTops();
            }

            @Override
            public boolean visitTop(String name, IntSpecMap<val_t> val) {
                val.accept(visitor_conv);
                return true;
            }

            @Override
            public void endTops() {
                visitor.endTops();
            }

            @Override
            public boolean beginRanges() {
                return visitor.beginRanges();
            }

            @Override
            public boolean visitRange(IRange<? extends String> rangeKey, IntSpecMap<val_t> val) {
                val.accept(visitor_conv);
                return true;
            }

            @Override
            public void endRanges() {
                visitor.endRanges();
            }

            @Override
            public void visitDefault(IntSpecMap<val_t> value) {
                value.accept(visitor_conv);
            }

            @Override
            public boolean beginValue(SpecLayer layer, Object layerKey) {
                return visitor.beginValue(layer, layerKey);
            }

            @Override
            public void visitValue(IntSpecMap<val_t> value) {
                value.accept(visitor_conv);
            }

            @Override
            public void endValue() {
                visitor.endValue();
            }
        });
    }

}
