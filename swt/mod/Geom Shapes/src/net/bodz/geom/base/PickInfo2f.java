package net.bodz.geom.base;

import net.bodz.geom.shape.Shape2f;

public class PickInfo2f {

    public Shape2f pick;
    public float   distance;

    public PickInfo2f(Shape2f pick, float distance) {
        this.pick = pick;
        this.distance = distance;
    }

    public PickInfo2f(Shape2f pick) {
        this.pick = pick;
        this.distance = 0;
    }

}
