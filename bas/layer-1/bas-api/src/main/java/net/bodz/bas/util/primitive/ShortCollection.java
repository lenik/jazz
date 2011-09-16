package net.bodz.bas.util.primitive;

import java.util.Collection;

public class ShortCollection {

    public static short[] toArray(Collection<Short> collection) {
        int size = collection.size();
        short[] array = new short[size];
        int i = 0;
        for (Short boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
