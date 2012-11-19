package net.bodz.bas.db.stat;

import java.util.Collection;

public interface ICounterGroup {

    Collection<ICounter<?>> getCounters();

    <T> ICounter<T> getCounter(String name);

}
