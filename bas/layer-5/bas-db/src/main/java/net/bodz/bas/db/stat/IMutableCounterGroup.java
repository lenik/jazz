package net.bodz.bas.db.stat;

public interface IMutableCounterGroup
        extends ICounterGroup {

    void putCounter(String name, ICounter<?> counter);

}
