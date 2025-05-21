package net.bodz.bas.make;

import java.util.Map;

import net.bodz.bas.proxy.java.util.DecoratedMap;
import net.bodz.bas.repr.form.SortOrder;

public class DataMap<K, T>
        extends DecoratedMap<K, IDataEntry<K, T>> {

    public DataMap() {
        this(SortOrder.KEEP);
    }

    public DataMap(SortOrder order) {
        this(order.newMapDefault());
    }

    public DataMap(Map<K, IDataEntry<K, T>> _orig) {
        super(_orig);
    }


}
