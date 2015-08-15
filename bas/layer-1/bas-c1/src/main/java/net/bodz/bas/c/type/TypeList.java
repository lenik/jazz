package net.bodz.bas.c.type;

import java.util.ArrayList;
import java.util.Collections;

public class TypeList<T>
        extends ArrayList<Class<? extends T>> {

    private static final long serialVersionUID = 1L;

    TypeSpace typeSpace = TypeSpace.getDefault();

    public Class<? extends T> findNearest(Class<?> t) {
        int mindist = Integer.MAX_VALUE;
        Class<? extends T> min = null;
        int n = size();
        for (int i = 0; i < n; i++) {
            Class<? extends T> x = get(i);
            int dist = typeSpace.dist(x, t);
            if (dist < mindist) {
                mindist = dist;
                min = x;
            }
        }
        return min;
    }

    public void sortByDistance(Class<?> base) {
        TypeDistanceComparator c = new TypeDistanceComparator(base);
        Collections.sort(this, c);
    }

}
