package net.bodz.bas.lang;

import java.lang.reflect.Method;

import net.bodz.bas.exceptions.IllegalUsageException;

public class Nullables {

    /**
     * @return <code>true</code> if both a and b are <code>null</code>, or <code>a.equals(b)</code>
     */
    public static boolean equals(Object a, Object b) {
        if (a == null || b == null)
            return a == b;
        return a.equals(b);
    }

    /**
     * @return <code>true</code> if both a and b are <code>null</code>, or <code>a.equals(b)</code>
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equalsIgnoreCase(b);
    }

    /**
     * @return <code>null</code> if <code>o</code> is <code>null</code>, or the return value of
     *         {@link Object#toString()}
     */
    public static String toString(Object o) {
        if (o == null)
            return null;
        return o.toString();
    }

    /**
     * @return 0 if <code>o</code> is <code>null</code>, or the return value of
     *         {@link Object#hashCode()}.
     */
    public int hashCode(Object o) {
        if (o == null)
            return 0;
        return o.hashCode();
    }

    public Class<?> getClass(Object o) {
        if (o == null)
            return null;
        return o.getClass();
    }

    public Object clone(Cloneable o) {
        if (o == null)
            return null;
        try {
            Method cloneMethod = o.getClass().getMethod("clone");
            Object cloned = cloneMethod.invoke(o);
            return cloned;
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(e.getMessage(), e);
        }
    }

}
