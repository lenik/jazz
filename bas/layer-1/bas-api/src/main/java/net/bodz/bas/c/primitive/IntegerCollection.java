package net.bodz.bas.c.primitive;

import java.util.Collection;

public class IntegerCollection {

    public static int[] toArray(Collection<Integer> collection) {
        int size = collection.size();
        int[] array = new int[size];
        int i = 0;
        for (Integer boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
