package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MultipleTargets
        extends ArrayList<Object> {

    private static final long serialVersionUID = 1L;

    public MultipleTargets() {
        super();
    }

    public MultipleTargets(Collection<? extends Object> c) {
        super(c);
    }

    public MultipleTargets(int initialCapacity) {
        super(initialCapacity);
    }

    public Object getFirstOrNull() {
        if (isEmpty())
            return null;
        else
            return get(0);
    }

    /**
     * @param objs
     *            <code>null</code> item is ignored.
     * @return <code>null</code> if all objs are <code>null</code>s.
     */
    public static MultipleTargets fromNonNulls(Object... objs) {
        MultipleTargets targets = new MultipleTargets(objs.length);
        for (Object obj : objs)
            if (obj != null)
                targets.add(obj);
        return targets.isEmpty() ? null : targets;
    }

    public static MultipleTargets from(IPathArrival arrival) {
        if (arrival.isMultiple()) {
            return (MultipleTargets) arrival.getTarget();
        } else {
            MultipleTargets targets = new MultipleTargets(1);
            Object target = arrival.getTarget();
            targets.add(target);
            return targets;
        }
    }

    public static List<IPathArrival> extract(IPathArrival arrival) {
        if (arrival == null)
            return Collections.emptyList();
        if (!arrival.isMultiple())
            return Arrays.asList(arrival);

        MultipleTargets targets = (MultipleTargets) arrival.getTarget();
        List<IPathArrival> arrivals = new ArrayList<>(targets.size());
        for (Object target : targets) {
            PathArrival clone = new PathArrival(arrival);
            clone.setTarget(target);
            clone.setMultiple(false);
            arrivals.add(clone);
        }
        return arrivals;
    }

}
