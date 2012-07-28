package net.bodz.geom.opt;

import java.util.HashSet;
import java.util.Set;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IRectangle2f;

public class Div4Tree2f {

    // Quadrants
    public static final int Q1 = 0;
    public static final int Q2 = 1;
    public static final int Q3 = 2;
    public static final int Q4 = 3;

    protected IRectangle2f bound;

    protected Set<?> captured = new HashSet<Object>();

    protected Div4Tree2f[] subs = new Div4Tree2f[4];

    public void addShape(IShape2f shape) {
        // shape.intersects()
    }
}
