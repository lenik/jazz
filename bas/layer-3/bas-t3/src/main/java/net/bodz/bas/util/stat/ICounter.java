package net.bodz.bas.util.stat;

import java.io.Serializable;

public interface ICounter<T extends Number>
        extends Serializable {

    ICounterDef<T> getDefinition();

    String getName();

    T getValue();

    void setValue(T value);

    void increase();

    void decrease();

}
