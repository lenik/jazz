package net.bodz.bas.db.stat;

public interface IEditableCounterGroup
        extends ICounterGroup {

    void putCounter(String name, ICounter<?> counter);

}
