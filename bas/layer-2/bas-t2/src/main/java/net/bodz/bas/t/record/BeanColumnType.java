package net.bodz.bas.t.record;

import net.bodz.bas.meta.decl.NotNull;

public class BeanColumnType<T, E>
        implements IColumnType<T, E> {

    final Class<E> type = null;
    final IColumnGetter<T, E> getter;
    final IColumnSetter<T, E> setter;

    BeanColumnType(IColumnGetter<T, E> getter, IColumnSetter<T, E> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public static <T, E> BeanColumnType<T, E> ofProperty(@NotNull IColumnGetter<T, E> getter, @NotNull IColumnSetter<T, E> setter) {
        return new BeanColumnType<>(getter, setter);
    }

    /** read-only property */
    public static <T, E> BeanColumnType<T, E> ofProperty(@NotNull IColumnGetter<T, E> getter) {
        return new BeanColumnType<>(getter, null);
    }

    /** write-only property */
    public static <T, E> BeanColumnType<T, E> ofProperty(@NotNull IColumnSetter<T, E> setter) {
        return new BeanColumnType<>(null, setter);
    }

    @Override
    public Class<E> getColumnType() {
        return type;
    }

    @Override
    public E get(T context) {
        if (getter != null)
            return getter.get(context);
        else
            return null;
    }

    @Override
    public void set(T context, E value) {
        if (setter != null)
            setter.set(context, value);
    }

}
