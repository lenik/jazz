package net.bodz.bas.c.primitive;

import java.util.Collection;

public class BooleanCollection {

    public static boolean[] toArray(Collection<Boolean> collection) {
        int size = collection.size();
        boolean[] array = new boolean[size];
        int i = 0;
        for (Boolean boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
