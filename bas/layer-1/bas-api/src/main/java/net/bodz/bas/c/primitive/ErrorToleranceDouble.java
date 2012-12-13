package net.bodz.bas.c.primitive;

import javax.annotation.Generated;

public class ErrorToleranceDouble
        extends Number {

    private static final long serialVersionUID = 1L;

    private final double min;
    private final double max;

    public ErrorToleranceDouble(double min, double max) {
        assert min <= max : "min<=max";
        this.min = min;
        this.max = max;
    }

    public static ErrorToleranceDouble valueOf(double value) {
        return new ErrorToleranceDouble(value, value);
    }

    public static ErrorToleranceDouble blur(double hit) {
        double ulp = Math.ulp(hit);
        return new ErrorToleranceDouble(hit - ulp, hit + ulp);
    }

    public static ErrorToleranceDouble blur(double hit, double radius) {
        return new ErrorToleranceDouble(hit - radius, hit + radius);
    }

    /** [ closedMin, closedMax ] */
    static ErrorToleranceDouble CC(double min, double max) {
        return new ErrorToleranceDouble(min, max);
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

    public boolean intersects(ErrorToleranceDouble r) {
        return (r.max >= min && r.max <= max) //
                || (r.min >= min && r.min <= max);
    }

    public boolean contains(ErrorToleranceDouble r) {
        return (r.min >= min && r.max <= max);
    }

    public boolean withIn(ErrorToleranceDouble r) {
        return (min >= r.min && max <= r.max);
    }

    public ErrorToleranceDouble intersect(ErrorToleranceDouble r) {
        double m = Math.max(min, r.min);
        double x = Math.min(max, r.max);
        return m <= x ? CC(m, x) : null;
    }

    public ErrorToleranceDouble union(ErrorToleranceDouble r) {
        double m = Math.min(min, r.min);
        double x = Math.max(max, r.max);
        return CC(m, x);
    }

    static final int PP = 0;
    static final int NP = 2;
    static final int NN = 3;

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

    int comb2(ErrorToleranceDouble r) {
        return comb() * 4 + r.comb();
    }

    public ErrorToleranceDouble neg() {
        return CC(-max, -min);
    }

    public ErrorToleranceDouble add(double r) {
        return CC(min + r, max + r);
    }

    public ErrorToleranceDouble add(ErrorToleranceDouble r) {
        return CC(min + r.min, max + r.max);
    }

    public ErrorToleranceDouble sub(double r) {
        return CC(min - r, max - r);
    }

    public ErrorToleranceDouble sub(ErrorToleranceDouble r) {
        return CC(min - r.max, max - r.min);
    }

    public ErrorToleranceDouble rsub(double r) {
        return CC(r - max, r - min);
    }

    public ErrorToleranceDouble rsub(ErrorToleranceDouble r) {
        return CC(r.min - max, r.max - min);
    }

    @Generated("mul")
    public ErrorToleranceDouble mul(double r) {
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

    public ErrorToleranceDouble mul(ErrorToleranceDouble r) {
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

    public ErrorToleranceDouble inv() {
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

    public ErrorToleranceDouble div(double r) {
        return mul(1 / r);
    }

    public ErrorToleranceDouble div(ErrorToleranceDouble r) {
        return mul(r.inv());
    }

    public ErrorToleranceDouble rdiv(double r) {
        return inv().mul(r);
    }

    public ErrorToleranceDouble rdiv(ErrorToleranceDouble r) {
        return inv().mul(r);
    }

    public ErrorToleranceDouble floor() {
        return CC(Math.floor(min), Math.floor(max));
    }

    public ErrorToleranceDouble ceil() {
        return CC(Math.ceil(min), Math.ceil(max));
    }

    public ErrorToleranceDouble round() {
        return CC(Math.round(min), Math.round(max));
    }

    public ErrorToleranceDouble abs() {
        if (min < 0 || max < 0) {
            double minP = Math.abs(min);
            double maxP = Math.abs(max);
            return minP <= maxP ? CC(minP, maxP) : CC(maxP, minP);
        }
        return this;
    }

    public ErrorToleranceDouble pow(double exp) {
        double a = Math.pow(min, exp);
        double b = Math.pow(max, exp);
        return a <= b ? CC(a, b) : CC(b, a);
    }

    public ErrorToleranceDouble log() {
        // assert min > 0 && max > 0;
        return CC(Math.log(min), Math.log(max));
    }

}
