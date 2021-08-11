package net.bodz.bas.t.variant;

import java.util.Map;

public interface ILookupList<V> {

    int size();

    /**
     * @see Map#get(Object)
     */
    V get(int index);

}
