package net.bodz.bas.geom_f.api;

public class PickResult2d {

    IShape2d shape;
    float distance;

    public PickResult2d(IShape2d shape, float distance) {
        this.shape = shape;
        this.distance = distance;
    }

    public PickResult2d(IShape2d pick) {
        this.shape = pick;
        this.distance = Float.NaN;
    }

    public IShape2d getShape() {
        return shape;
    }

    public void setShape(IShape2d shape) {
        this.shape = shape;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

}
