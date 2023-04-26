package net.bodz.bas.t.specmap;

public interface IValueHolder<val_t> {

    boolean hasValue();

    val_t getValue();

    val_t putValue(val_t value);

    boolean addValue(val_t value);

    default val_t getOrAddValue(val_t initial) {
        if (addValue(initial))
            return initial;
        else
            return getValue();
    }

    val_t removeValue();

}
