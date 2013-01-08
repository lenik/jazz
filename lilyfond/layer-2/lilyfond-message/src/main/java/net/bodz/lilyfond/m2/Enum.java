package net.bodz.lilyfond.m2;

import java.io.ObjectStreamException;
import java.io.Serializable;

import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(publishDir = "META-INF/enums")
public abstract class Enum<E extends Enum<E>>
        extends AbstractElement
        implements Serializable, Comparable<E> {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int ordinal;

    public Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public String name() {
        return name;
    }

    public int ordinal() {
        return ordinal;
    }

    public EnumClass<? extends E> getEnumClass() {
        Class<? extends E> type = (Class<? extends E>) getClass();
        EnumClass<? extends E> enumClass = EnumClass.getEnumClass(type);
        return enumClass;
    }

    protected static <E extends Enum<E>> E nameOf(Class<E> enumType, String name) {
        return null;
    }

    E readResolve()
            throws ObjectStreamException {
        return getEnumClass().getDeclaredValue(ordinal);
    }

}
