package net.bodz.bas.db.stat;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.string.StringArray;

public class StatNode
        extends AbstractCounterGroup {

    private static final long serialVersionUID = 1L;

    private final StatNode parent;
    private final String name;
    private final Map<String, StatNode> children = new LinkedHashMap<>();

    public StatNode() {
        this(null, null);
    }

    public StatNode(ICounterSpec spec) {
        this(null, null, spec);
    }

    public StatNode(StatNode parent, String name) {
        this(parent, name, null);
    }

    public StatNode(StatNode parent, String name, ICounterSpec spec) {
        this.parent = parent;
        this.name = name;

        if (spec != null)
            for (ICounterDef<?> def : spec.getDefinitions()) {
                String counterName = def.getName();
                ICounter<?> mainCounter = def.createCounter(counterName);
                putCounter(def.getName(), mainCounter);
            }
    }

    public String getName() {
        return name;
    }

    public StatNode getParent() {
        return parent;
    }

    public Map<String, StatNode> getChildMap() {
        return children;
    }

    public StatNode getChild(String name) {
        return children.get(name);
    }

    public synchronized StatNode getOrDeriveChild(String name) {
        StatNode childNode = children.get(name);
        if (childNode == null) {
            childNode = new StatNode(this, name);

            // initialize sub counters.
            for (ICounter<?> counter : getCounters()) {
                ICounterDef<?> def = counter.getDefinition();
                String counterName = counter.getName();
                ICounter<?> subCounter = def.createSubCounter(counter, counterName);
                childNode.putCounter(counterName, subCounter);
            }

            children.put(name, childNode);
        }
        return childNode;
    }

    public <T> ICounter<T> resolveCounter(String path) {
        return resolveCounter(path, false);
    }

    public <T> ICounter<T> resolveCounter(String path, boolean autoDerive) {
        if (path == null)
            throw new NullPointerException("path");

        String[] entries = StringArray.splitRaw(path, '/');
        String lastEntry = entries[entries.length - 1];

        StatNode node = resolve(entries, 0, entries.length - 1, autoDerive);
        if (node == null)
            return null;

        ICounter<T> counter = (ICounter<T>) node.getCounter(lastEntry);
        return counter;
    }

    StatNode resolve(String[] entries, int off, int len, boolean autoDerive) {
        StatNode node = this;
        for (int i = off; i < len; i++) {
            String entry = entries[i];
            if (autoDerive)
                node = node.getOrDeriveChild(entry);
            else {
                node = node.getChild(entry);
                if (node == null)
                    break;
            }
        }
        return node;
    }

}
