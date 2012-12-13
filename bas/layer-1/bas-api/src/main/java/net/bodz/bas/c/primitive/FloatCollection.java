package net.bodz.bas.c.primitive;

import java.util.Collection;

public class FloatCollection {

    public static float[] toArray(Collection<Float> collection) {
        int size = collection.size();
        float[] array = new float[size];
        int i = 0;
        for (Float boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
