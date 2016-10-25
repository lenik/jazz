package net.bodz.bas.util.stat;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class AbstractCounterGroup
        implements IMutableCounterGroup, Serializable {

    private static final long serialVersionUID = 1L;

    Map<String, ICounter<?>> counterMap;

    public AbstractCounterGroup() {
        counterMap = new TreeMap<>();
    }

    @Override
    public Collection<ICounter<?>> getCounters() {
        return counterMap.values();
    }

    @Override
    public <T extends Number> ICounter<T> getCounter(String name) {
        ICounter<T> counter = (ICounter<T>) counterMap.get(name);
        return counter;
    }

    @Override
    public void putCounter(String name, ICounter<?> counter) {
        counterMap.put(name, counter);
    }

}
