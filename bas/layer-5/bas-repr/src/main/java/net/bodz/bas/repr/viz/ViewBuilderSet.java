package net.bodz.bas.repr.viz;

import java.util.TreeSet;

import net.bodz.bas.c.type.TypeSpace;
import net.bodz.bas.t.order.PriorityComparator;

public class ViewBuilderSet<T>
        extends TreeSet<IViewBuilder<T>> {

    private static final long serialVersionUID = 1L;

    private TypeSpace typeSpace = TypeSpace.getDefault();

    public ViewBuilderSet() {
        super(PriorityComparator.INSTANCE);
    }

    public IViewBuilder<T> getAny() {
        if (isEmpty())
            return null;
        return iterator().next();
    }

    public <X> X findFirst(Class<X> t) {
        for (Object x : this)
            if (t.isInstance(x))
                return t.cast(x);
        return null;
    }

    public IViewBuilder<T> findNearest(Class<?> t) {
        int mindist = Integer.MAX_VALUE;
        IViewBuilder<T> min = null;
        for (IViewBuilder<T> x : this) {
            int dist = typeSpace.dist(x.getClass(), t);
            if (dist < mindist) {
                mindist = dist;
                min = x;
            }
        }
        return min;
    }

}
