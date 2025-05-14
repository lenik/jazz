package net.bodz.bas.t.model;

public class Wrappers {

    public static boolean isThatOrWrappedInstanceOf(Object that, Class<?> t) {
        if (t.isInstance(that))
            return true;
        if (that instanceof IWrapper)
            return ((IWrapper<?>) that).isThisOrWrappedInstanceOf(t);
        return false;
    }

    public static <T> T castThatOrWrappedTo(Object that, Class<T> t) {
        if (t.isInstance(that))
            return t.cast(that);
        if (that instanceof IWrapper)
            return ((IWrapper<?>) that).castThisOrWrappedTo(t);
        throw new ClassCastException("not an instance");
    }

}
