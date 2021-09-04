package net.bodz.bas.t.map;

import net.bodz.bas.t.set.IMarkset;

public interface IMarksetWithPath
        extends
            IMarkset {

    void enter(String name);

    void leave();

    String getContextPath();

    String path(String name);

    String lookup(Object o);

    @Override
    default boolean addMark(Object o) {
        String prev = addMark(o, null);
        return prev == null;
    }

    String addMark(Object o, String name);

    default boolean canEnter(String name, Object o) {
        return enter(name, o) == null;
    }

    default String enter(String name, Object o) {
        enter(name);
        String oldPath = addMark(o, null);
        if (oldPath != null)
            leave();
        return oldPath;
    }

}
