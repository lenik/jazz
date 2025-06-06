package net.bodz.bas.make.type;

import java.io.Serializable;
import java.util.Arrays;

import net.bodz.bas.meta.decl.NotNull;

public class Extends
        implements Serializable {

    private static final long serialVersionUID = -2731736366839633059L;

    Class<?>[] interfaces;
    public final int length;
    final int hashCode;

    public Extends(@NotNull Class<?>... interfaces) {
        this.interfaces = interfaces;
        this.length = interfaces.length;
        this.hashCode = Arrays.hashCode(interfaces);
    }

    public static Extends of(@NotNull Class<?>... interfaces) {
        return new Extends(interfaces);
    }

    public Class<?> get(int index) {
        return interfaces[index];
    }

    public boolean isInstance(Object o) {
        for (Class<?> iface : interfaces)
            if (!iface.isInstance(o))
                return false;
        return true;
    }

    public boolean isAssignableFrom(Class<?> rhsType) {
        for (Class<?> iface : interfaces)
            if (!iface.isAssignableFrom(rhsType))
                return false;
        return true;
    }

    public boolean isAssignableFrom(@NotNull Class<?>... rhsTypes) {
L:
        for (Class<?> iface : interfaces) {
            for (Class<?> rhsType : rhsTypes)
                if (iface.isAssignableFrom(rhsType))
                    continue L;
            return false;
        }
        return true;
    }

    public boolean isAssignableFrom(@NotNull Extends o) {
        return isAssignableFrom(o.interfaces);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Extends multiType = (Extends) o;
        return Arrays.equals(interfaces, multiType.interfaces);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return Arrays.toString(interfaces);
    }

}
