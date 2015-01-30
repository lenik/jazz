package net.bodz.bas.t.order;

public class PriorityUtils {

    public static <T extends IPriority> T selectTop(Iterable<T> v) {
        if (v == null)
            throw new NullPointerException("v");

        T selection = null;
        int priority = 0;

        for (T o : v)
            if (selection == null || o.getPriority() < priority) {
                selection = o;
                priority = o.getPriority();
            }

        return selection;
    }

    public static <T extends IPriority> T selectBottom(Iterable<T> v) {
        if (v == null)
            throw new NullPointerException("v");

        T selection = null;
        int priority = 0;

        for (T o : v)
            if (selection == null || o.getPriority() >= priority) {
                selection = o;
                priority = o.getPriority();
            }

        return selection;
    }

}
