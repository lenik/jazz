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

    public synchronized StatNode createChild(String name, SubCounterMode modeOverride) {
        StatNode childNode = new StatNode(this, name);

        // initialize sub counters.
        for (ICounter<?> counter : getCounters()) {
            String counterName = counter.getName();

            ICounterDef<?> def = counter.getDefinition();
            Number initValue = def.getZero();
            SubCounterMode mode = modeOverride != null ? modeOverride : def.getSubCounterMode();

            @SuppressWarnings("unchecked")//
            ICounter<Number> _counter = (ICounter<Number>) counter;

            ICounter<?> subCounter = mode.create(_counter, counterName, initValue);

            childNode.putCounter(counterName, subCounter);
        }

        children.put(name, childNode);
        return childNode;
    }

    public <T extends Number> ICounter<T> resolveCounter(String path) {
        return resolveCounter(path, false, null);
    }

    public <T extends Number> ICounter<T> resolveCounter(String path, boolean autoCreate) {
        return resolveCounter(path, autoCreate, null);
    }

    public <T extends Number> ICounter<T> resolveCounter(String path, boolean autoCreate, SubCounterMode modeOverride) {
        if (path == null)
            throw new NullPointerException("path");

        String[] entries = StringArray.splitRaw(path, '/');
        String lastEntry = entries[entries.length - 1];

        StatNode node = resolve(entries, 0, entries.length - 1, autoCreate, modeOverride);
        if (node == null)
            return null;

        ICounter<T> counter = (ICounter<T>) node.getCounter(lastEntry);
        return counter;
    }

    StatNode resolve(String[] entries, int off, int len, boolean autoCreate, SubCounterMode modeOverride) {
        StatNode node = this;

        for (int i = off; i < len; i++) {
            String entry = entries[i];

            StatNode childNode = node.getChild(entry);

            if (childNode == null)
                if (autoCreate)
                    childNode = node.createChild(entry, modeOverride);
                else
                    return null;

            node = childNode;
        }
        return node;
    }

}
