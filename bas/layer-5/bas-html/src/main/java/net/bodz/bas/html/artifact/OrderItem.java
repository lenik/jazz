package net.bodz.bas.html.artifact;

import java.util.Comparator;

public class OrderItem {

    public IArtifact artifact;
    public int depth;
    public int priority;
    public int seq;

    public void update(IArtifact dep, int priority, int depth) {
        if (artifact == null) {
            this.artifact = dep;
            this.depth = depth;
            this.priority = priority;
        } else {
            this.depth = Math.max(this.depth, depth);
            this.priority = Math.min(this.priority, priority);
        }
    }

}

class OrderItemComparator
        implements Comparator<OrderItem> {

    @Override
    public int compare(OrderItem o1, OrderItem o2) {
        int cmp = o1.depth - o2.depth;
        if (cmp != 0)
            return -cmp; // order by depth descending

        cmp = o1.priority - o2.priority;
        if (cmp != 0)
            return cmp;

        cmp = o1.seq - o2.seq;
        return cmp;
    }

    static final OrderItemComparator INSTANCE = new OrderItemComparator();

}
