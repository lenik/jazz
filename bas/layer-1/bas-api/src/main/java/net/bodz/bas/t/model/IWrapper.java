package net.bodz.bas.t.model;

import net.bodz.bas.meta.decl.NotNull;

public interface IWrapper<T> {

    @NotNull
    T getWrapped();

    default boolean isThisOrWrappedInstanceOf(Class<?> t) {
        if (t.isInstance(this))
            return true;
        T wrapped = getWrapped();
        if (t.isInstance(wrapped))
            return true;
        if (wrapped instanceof IWrapper)
            return ((IWrapper<?>) wrapped).isThisOrWrappedInstanceOf(t);
        return false;
    }

    default <_t> _t castThisOrWrappedTo(Class<_t> t) {
        if (t.isInstance(this))
            return t.cast(this);
        T wrapped = getWrapped();
        if (t.isInstance(wrapped))
            return t.cast(wrapped);
        if (wrapped instanceof IWrapper)
            return ((IWrapper<?>) wrapped).castThisOrWrappedTo(t);
        throw new ClassCastException("not an instance");
    }

}
