package net.bodz.lily.model.res.art;

import java.io.Serializable;

/**
 * 三维尺寸
 */
public class Dim3d
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public double dx;
    public double dy;
    public double dz;

    public Dim3d() {
    }

    public Dim3d(double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public boolean isZero() {
        return dx == 0.0 && dy == 0.0 && dz == 0.0;
    }

    public double getVolume() {
        return dx * dy * dz;
    }

    /**
     * @label ΔX
     */
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * @label ΔY
     */
    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @label ΔZ
     */
    public double getDz() {
        return dz;
    }

    public void setDz(double dz) {
        this.dz = dz;
    }

    public String format(String delim) {
        return dx + delim + dy + delim + dz;
    }

    @Override
    public String toString() {
        return format(" x ");
    }

}
