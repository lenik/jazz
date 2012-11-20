package net.bodz.bas.db.stat;

import java.io.Serializable;

import net.bodz.bas.i18n.dom1.IElement;

public interface ICounterDef<T extends Number>
        extends IElement, Serializable {

    Class<T> getValueType();

    T getInitValue();

    int getPrecision();

    int getScale();

    String getUnit();

    SubCounterMode getSubCounterMode();

    ICounter<T> createCounter(String name);

    T add(T a, T b);

    T sub(T a, T b);

    T incr(T a);

    T decr(T a);

    T multiply(T a, int k);

    T multiply(T a, double k);

    T divide(T a, int k);

    T divide(T a, double k);

}
