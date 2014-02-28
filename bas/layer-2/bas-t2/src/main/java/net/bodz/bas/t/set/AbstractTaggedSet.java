package net.bodz.bas.t.set;

public abstract class AbstractTaggedSet<V>
        implements TaggedSet<V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

}
