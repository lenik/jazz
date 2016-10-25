package net.bodz.bas.util.stat;

import java.io.Serializable;

import net.bodz.bas.i18n.dom1.IElement;

public interface ICounterDef<T extends Number>
        extends IElement, Serializable {

    Class<T> getValueType();

    int getPrecision();

    int getScale();

    String getUnit();

    SubCounterMode getSubCounterMode();

    ICounter<T> createCounter(String name);

    boolean appxEquals(T a, T b);

    T getZero();

    T getOne();

    T add(T a, T b);

    T sub(T a, T b);

    T incr(T a);

    T decr(T a);

    T multiply(T a, int k);

    T multiply(T a, double k);

    T divide(T a, int k);

    T divide(T a, double k);

    /**
     * Get the min number.
     * 
     * @param a
     *            Non-<code>null</code> number.
     * @param a
     *            Non-<code>null</code> number.
     * @return The maximum number.
     */
    T min(T a, T b);

    /**
     * Get the max number.
     * 
     * @param a
     *            Non-<code>null</code> number.
     * @param a
     *            Non-<code>null</code> number.
     * @return The maximum number.
     */
    T max(T a, T b);

}
