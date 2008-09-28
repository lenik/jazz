package net.bodz.bas.lang;

import net.bodz.bas.lang.a.Derived;

public class RReal extends Number {

    private static final long serialVersionUID = -8213113619672707641L;

    private final double      min;
    private final double      max;

    public RReal(double min, double max) {
        assert min <= max : "min<=max";
        this.min = min;
        this.max = max;
    }

    public static RReal valueOf(double value) {
        return new RReal(value, value);
    }

    public static RReal blur(double hit) {
        double ulp = Math.ulp(hit);
        return new RReal(hit - ulp, hit + ulp);
    }

    public static RReal blur(double hit, double radius) {
        return new RReal(hit - radius, hit + radius);
    }

    /** [ closedMin, closedMax ] */
    static RReal CC(double min, double max) {
        return new RReal(min, max);
    }

    @Override
    public double doubleValue() {
        return (min + max) / 2;
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public String toString() {
        return "[" + min + "," + max + "]";
    }

    public double dia() {
        return max - min;
    }

    public boolean intersects(RReal r) {
        return (r.max >= min && r.max <= max) // 
                || (r.min >= min && r.min <= max);
    }

    public boolean contains(RReal r) {
        return (r.min >= min && r.max <= max);
    }

    public boolean withIn(RReal r) {
        return (min >= r.min && max <= r.max);
    }

    public RReal intersect(RReal r) {
        double m = Math.max(min, r.min);
        double x = Math.min(max, r.max);
        return m <= x ? CC(m, x) : null;
    }

    public RReal union(RReal r) {
        double m = Math.min(min, r.min);
        double x = Math.max(max, r.max);
        return CC(m, x);
    }

    static final int PP    = 0;
    static final int NP    = 2;
    static final int NN    = 3;

    static final int PP_PP = 0;
    static final int PP_NP = 2;
    static final int PP_NN = 3;
    static final int NP_PP = 8;
    static final int NP_NP = 10;
    static final int NP_NN = 11;
    static final int NN_PP = 12;
    static final int NN_NP = 14;
    static final int NN_NN = 15;

    int comb() {
        int m = min >= 0 ? 0 : 1;
        int x = max >= 0 ? 0 : 1;
        return m * 2 + x;
    }

    int comb2(double r) {
        int rc = r >= 0 ? 0 : 3;
        return comb() * 4 + rc;
    }

    int comb2(RReal r) {
        return comb() * 4 + r.comb();
    }

    public RReal neg() {
        return CC(-max, -min);
    }

    public RReal add(double r) {
        return CC(min + r, max + r);
    }

    public RReal add(RReal r) {
        return CC(min + r.min, max + r.max);
    }

    public RReal sub(double r) {
        return CC(min - r, max - r);
    }

    public RReal sub(RReal r) {
        return CC(min - r.max, max - r.min);
    }

    public RReal rsub(double r) {
        return CC(r - max, r - min);
    }

    public RReal rsub(RReal r) {
        return CC(r.min - max, r.max - min);
    }

    @Derived
    public RReal mul(double r) {
        switch (comb2(r)) {
        case PP_PP: // M-X
            return CC(min * r, max * r);
        case PP_NN: // X-M
            return CC(max * r, min * r);
        case NP_PP: // M-X
            return CC(min * r, max * r);
        case NP_NN: // X-M
            return CC(max * r, min * r);
        case NN_PP: // M-X
            return CC(min * r, max * r);
        case NN_NN: // X-M
            return CC(max * r, min * r);
        }
        return null;
    }

    public RReal mul(RReal r) {
        switch (comb2(r)) {
        case PP_PP: // MM-XX
            return CC(min * r.min, max * r.max);
        case PP_NP: // XM-XX
            return CC(max * r.min, max * r.max);
        case PP_NN: // XM-MX
            return CC(max * r.min, min * r.max);
        case NP_PP: // MX-XX
            return CC(min * r.max, max * r.max);
        case NP_NP: // min(MX,XM)-max(MM,XX)
            double m1 = min * r.max;
            double m2 = max * r.min;
            double x1 = min * r.min;
            double x2 = max * r.max;
            return CC(Math.min(m1, m2), Math.max(x1, x2));
        case NP_NN: // XM-MM
            return CC(max * r.min, min * r.min);
        case NN_PP: // MX-XM
            return CC(min * r.max, max * r.min);
        case NN_NP: // MX-MM
            return CC(min * r.max, min * r.min);
        case NN_NN: // XX-MM
            return CC(max * r.max, min * r.min);
        }
        return null;
    }

    public RReal inv() {
        switch (comb()) {
        case PP: // XM
            return CC(1 / max, 1 / min);
        case NP: // MX
            return CC(1 / min, 1 / max);
        case NN: // XM
            return CC(1 / max, 1 / min);
        }
        return null;
    }

    public RReal div(double r) {
        return mul(1 / r);
    }

    public RReal div(RReal r) {
        return mul(r.inv());
    }

    public RReal rdiv(double r) {
        return inv().mul(r);
    }

    public RReal rdiv(RReal r) {
        return inv().mul(r);
    }

    public RReal floor() {
        return CC(Math.floor(min), Math.floor(max));
    }

    public RReal ceil() {
        return CC(Math.ceil(min), Math.ceil(max));
    }

    public RReal round() {
        return CC(Math.round(min), Math.round(max));
    }

    public RReal abs() {
        if (min < 0 || max < 0) {
            double minP = Math.abs(min);
            double maxP = Math.abs(max);
            return minP <= maxP ? CC(minP, maxP) : CC(maxP, minP);
        }
        return this;
    }

    public RReal pow(double exp) {
        double a = Math.pow(min, exp);
        double b = Math.pow(max, exp);
        return a <= b ? CC(a, b) : CC(b, a);
    }

    public RReal log() {
        // assert min > 0 && max > 0;
        return CC(Math.log(min), Math.log(max));
    }

}
