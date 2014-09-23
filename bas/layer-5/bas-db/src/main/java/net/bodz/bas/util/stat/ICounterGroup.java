package net.bodz.bas.util.stat;

import java.util.Collection;

public interface ICounterGroup {

    Collection<ICounter<?>> getCounters();

    <T extends Number> ICounter<T> getCounter(String name);

}
