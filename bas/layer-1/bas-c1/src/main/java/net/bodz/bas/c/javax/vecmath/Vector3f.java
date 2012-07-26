package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;

public class Vector3f
        extends javax.vecmath.Vector3f {

    private static final long serialVersionUID = 1L;

    public Vector3f() {
        super();
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(float[] init) {
        super(init);
    }

    public Vector3f(Tuple3d init) {
        super(init);
    }

    public Vector3f(Tuple3f init) {
        super(init);
    }

    public Vector3f(Vector3d init) {
        super(init);
    }

    public Vector3f(javax.vecmath.Vector3f init) {
        super(init);
    }

    public Vector3f(Vector2f vector2f) {
        super(vector2f.x, vector2f.y, 1.0f);
    }

    /*
     * public Vector3f(Point2f point2f) { super(point2f.x(), point2f.y(), 1.0f); }
     */
}
