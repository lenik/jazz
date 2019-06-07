package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Collections;

public class PathArrivalList
        extends ArrayList<IPathArrival> {

    private static final long serialVersionUID = 1L;

    boolean leftToRight = true;

    public PathArrivalList() {
        super();
    }

    public PathArrivalList(int initialCapacity) {
        super(initialCapacity);
    }

    public PathArrivalList leftToRight() {
        if (!leftToRight) {
            Collections.reverse(this);
            leftToRight = true;
        }
        return this;
    }

    public PathArrivalList rightToLeft() {
        if (leftToRight) {
            Collections.reverse(this);
            leftToRight = false;
        }
        return this;
    }

    public PathArrivalList mergeTransients() {
        PathArrivalList merged = PathArrival.merge(this);
        return merged;
    }

}
