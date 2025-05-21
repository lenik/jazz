package net.bodz.bas.make.util;

import java.util.Comparator;
import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public class SetKey<E>
        implements Comparator<SetKey<E>> {

    final Class<E> elementType;

    public SetKey(@NotNull Class<E> elementType) {
        this.elementType = elementType;
    }

    @NotNull
    public Class<E> getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        SetKey<?> listKey = (SetKey<?>) o;
        return Objects.equals(elementType, listKey.elementType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elementType);
    }

    @Override
    public String toString() {
        return elementType.toString() + " Set";
    }

    @Override
    public int compare(SetKey<E> o1, SetKey<E> o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;
        if (o2 == null)
            return 1;
        String type1 = o1.elementType.toString();
        String type2 = o2.elementType.toString();
        return type1.compareTo(type2);
    }

}
