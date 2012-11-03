package net.bodz.bas.geom.spec0_f;

public class PickResult2d {

    IPrimitive2d shape;
    float distance;

    public PickResult2d(IPrimitive2d shape, float distance) {
        this.shape = shape;
        this.distance = distance;
    }

    public PickResult2d(IPrimitive2d pick) {
        this.shape = pick;
        this.distance = Float.NaN;
    }

    public IPrimitive2d getShape() {
        return shape;
    }

    public void setShape(IPrimitive2d shape) {
        this.shape = shape;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

}
