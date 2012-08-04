package net.bodz.geom.base;

import net.bodz.geom.shape.IShape2f;

public class PickInfo2f {

    public IShape2f pick;
    public float distance;

    public PickInfo2f(IShape2f pick, float distance) {
        this.pick = pick;
        this.distance = distance;
    }

    public PickInfo2f(IShape2f pick) {
        this.pick = pick;
        this.distance = 0;
    }

}
