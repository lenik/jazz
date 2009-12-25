package net.bodz.bas.commons.math;

import net.bodz.bas.api.exceptions.NotImplementedException;

public class RealMath {

    public static int roundFloor(double v) {
        return (int) Math.nextUp(v);
    }

    public static int roundFloor(float v) {
        return (int) Math.nextUp(v);
    }

    public static int roundCeil(double v) {
        return (int) Math.ceil(Math.nextAfter(v, Double.NEGATIVE_INFINITY));
    }

    public static int roundCeil(float v) {
        return (int) Math.ceil(Math.nextAfter(v, Float.NEGATIVE_INFINITY));
    }

    public static double fac(double n) {
        throw new NotImplementedException();
    }

}
