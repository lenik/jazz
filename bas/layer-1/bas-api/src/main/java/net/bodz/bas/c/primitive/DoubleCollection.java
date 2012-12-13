package net.bodz.bas.c.primitive;

import java.util.Collection;

public class DoubleCollection {

    public static double[] toArray(Collection<Double> collection) {
        int size = collection.size();
        double[] array = new double[size];
        int i = 0;
        for (Double boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
