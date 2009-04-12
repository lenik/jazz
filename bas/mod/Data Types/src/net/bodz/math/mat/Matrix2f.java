package net.bodz.math.mat;

import java.io.Serializable;

public class Matrix2f implements Serializable {

    static final long serialVersionUID = 948093791490130460L;

    public float      x00;
    public float      x01;
    public float      x10;
    public float      x11;

    public Matrix2f() {
        x00 = 1.0f;
        x01 = 0.0f;
        x10 = 0.0f;
        x11 = 1.0f;
    }

    public Matrix2f(float x00, float x01, float x10, float x11) {
        this.x00 = x00;
        this.x01 = x01;
        this.x10 = x10;
        this.x11 = x11;
    }

    public final void add(float k) {
        x00 += k;
        x01 += k;
        x10 += k;
        x11 += k;
    }

    public final void sub(float k) {
        x00 -= k;
        x01 -= k;
        x10 -= k;
        x11 -= k;
    }

    public final void subBy(float k) {
        x00 = k - x00;
        x01 = k - x01;
        x10 = k - x10;
        x11 = k - x11;
    }

    public final void mul(float k) {
        x00 *= k;
        x01 *= k;
        x10 *= k;
        x11 *= k;
    }

    public final void div(float k) {
        x00 /= k;
        x01 /= k;
        x10 /= k;
        x11 /= k;
    }

    public final void divBy(float k) {
        x00 = k / x00;
        x01 = k / x01;
        x10 = k / x10;
        x11 = k / x11;
    }

    public final void add(Matrix2f m) {
        x00 += m.x00;
        x01 += m.x01;
        x10 += m.x10;
        x11 += m.x11;
    }

    public final void sub(Matrix2f m) {
        x00 -= m.x00;
        x01 -= m.x01;
        x10 -= m.x10;
        x11 -= m.x11;
    }

    /**
     * this * m
     */
    public final void mul(Matrix2f m) {
        x00 = x00 * m.x00 + x01 * m.x10;
        x01 = x00 * m.x01 + x01 * m.x11;
        x10 = x10 * m.x00 + x11 * m.x10;
        x11 = x10 * m.x01 + x11 * m.x11;
    }

    /**
     * m * this
     */
    public final void mulBy(Matrix2f m) {
        x00 = m.x00 * x00 + m.x01 * x10;
        x01 = m.x00 * x01 + m.x01 * x11;
        x10 = m.x10 * x00 + m.x11 * x10;
        x11 = m.x10 * x01 + m.x11 * x11;
    }

    protected final boolean isSingular() {
        if (x00 == 0.0f) {
            if (x01 == 0.0f || x10 == 0.0f)
                return true;
            return false;
        }
        if (x11 == 0.0f) {
            if (x01 == 0.0f || x10 == 0.0f)
                return true;
            return false;
        }
        return false;
    }

    public final void inv() {
        float D = x00 * x11 - x01 * x10;
        x00 = x11 / D;
        x01 = -x01 / D;
        x10 = -x10 / D;
        x11 = x00 / D;
    }

    /**
     * this / m
     * 
     * [-m01 n10 + m00 n11 m00 n01 - n00 m01 ] [------------------ -
     * ----------------- ] [n00 n11 - n01 n10 n00 n11 - n01 n10 ] [ ] [-m11 n10
     * + m10 n11 -n00 m11 + m10 n01] [------------------ - ------------------]
     * [n00 n11 - n01 n10 n00 n11 - n01 n10 ]
     */
    public final void div(Matrix2f m) {
        float D = m.x00 * m.x11 - m.x01 * m.x10;
        x00 = x00 * m.x11 - x01 * m.x10;
        x01 = -x00 * m.x01 + x01 * m.x00;
        x10 = x10 * m.x11 - x11 * m.x10;
        x11 = -x10 * m.x01 + x11 * m.x00;
        x00 /= D;
        x01 /= D;
        x10 /= D;
        x11 /= D;
    }

    /**
     * m / this
     */
    public final void divBy(Matrix2f m) {
        float D = x00 * x11 - x01 * x10;
        x00 = m.x00 * x11 - m.x01 * x10;
        x01 = -m.x00 * x01 + m.x01 * x00;
        x10 = m.x10 * x11 - m.x11 * x10;
        x11 = -m.x10 * x01 + m.x11 * x00;
        x00 /= D;
        x01 /= D;
        x10 /= D;
        x11 /= D;
    }

    public final void dotMul(Matrix2f m) {
        x00 *= m.x00;
        x01 *= m.x01;
        x10 *= m.x10;
        x11 *= m.x11;
    }

    public final void dotDiv(Matrix2f m) {
        x00 /= m.x00;
        x01 /= m.x01;
        x10 /= m.x10;
        x11 /= m.x11;
    }

    public final void dotDivBy(Matrix2f m) {
        x00 = m.x00 / x00;
        x01 = m.x01 / x01;
        x10 = m.x10 / x10;
        x11 = m.x11 / x11;
    }

    public final void transpose() {
        float t = x01;
        x01 = x10;
        x10 = t;
    }

    @Override
    public String toString() {
        return String.format("[ %10.4f %10.4f ]\n" + "[ %10.4f %10.4f ]\n", //$NON-NLS-1$ //$NON-NLS-2$
                x00, x01, x10, x11);
    }

}
