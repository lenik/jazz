package net.bodz.bas.collection.preorder;

import java.nio.file.Path;

public class PathStartswithPreorder
        extends AbstractPreorder<Path> {

    /**
     * @throws NullPointerException
     *             if path is <code>null</code>.
     */
    @Override
    public Path getPreceding(Path path) {
        return path.getParent();
    }

    @Override
    public int precompare(Path p1, Path p2) {
        // int ncmp = p1.getNameCount()-p2.getNameCount();
        if (p1.startsWith(p2))
            return GREATER_THAN;
        if (p2.startsWith(p1))
            return LESS_THAN;
        return UNKNOWN;
    }

    static final PathStartswithPreorder instance = new PathStartswithPreorder();

    public static PathStartswithPreorder getInstance() {
        return instance;
    }

}
