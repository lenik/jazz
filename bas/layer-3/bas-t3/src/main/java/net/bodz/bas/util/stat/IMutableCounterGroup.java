package net.bodz.bas.util.stat;

public interface IMutableCounterGroup
        extends ICounterGroup {

    void putCounter(String name, ICounter<?> counter);

}
