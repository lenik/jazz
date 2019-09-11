package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.PrintOutImpl;

public class MapDumper {

    private IPrintOut out;
    private Comparator<Object> comparator;

    public MapDumper() {
        this(Stdio.cout);
    }

    public MapDumper(ICharOut out) {
        this.out = PrintOutImpl.from(out);
    }

    public MapDumper(IPrintOut out) {
        this.out = out;
    }

    public <K> void dump(Map<K, ?> map) {
        Collection<K> keys = map.keySet();
        if (comparator != null) {
            List<K> sortedList = new ArrayList<K>(keys);
            Collections.sort(sortedList, comparator);
            keys = sortedList;
        }

        for (K key : keys) {
            Object value = map.get(key);
            out.print(key);
            out.print(": ");
            out.print(value);
            out.println();
        }
    }

}
