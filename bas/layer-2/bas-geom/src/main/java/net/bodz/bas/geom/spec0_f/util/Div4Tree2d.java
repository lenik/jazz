package net.bodz.bas.geom.spec0_f.util;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec1_f.IRectangle2d;

public class Div4Tree2d {

    // Quadrants
    public static final int Q1 = 0;
    public static final int Q2 = 1;
    public static final int Q3 = 2;
    public static final int Q4 = 3;

    protected IRectangle2d bound;

    protected Set<?> captured = new HashSet<Object>();

    protected Div4Tree2d[] subs = new Div4Tree2d[4];

    public void addShape(IPrimitive2d shape) {
        // shape.intersects()
    }
}
