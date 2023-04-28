package net.bodz.bas.t.specmap;

public interface IDefaultHolder<val_t> {

    boolean hasDefault();

    val_t getDefault();

    val_t putDefault(val_t value);

    default boolean addDefault(val_t value) {
        if (hasDefault())
            return false;
        putDefault(value);
        return true;
    }

    default val_t getOrAddDefault(val_t initial) {
        if (addDefault(initial))
            return initial;
        else
            return getDefault();
    }

    val_t removeDefault();

}
