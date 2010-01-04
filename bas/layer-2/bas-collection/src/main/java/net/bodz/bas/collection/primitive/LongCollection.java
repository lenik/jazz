package net.bodz.bas.collection.primitive;

import java.util.Collection;

public class LongCollection {

    public static long[] toArray(Collection<Long> collection) {
        int size = collection.size();
        long[] array = new long[size];
        int i = 0;
        for (Long boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
