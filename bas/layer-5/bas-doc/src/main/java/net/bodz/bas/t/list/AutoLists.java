package net.bodz.bas.t.list;

public class AutoLists {

    public static <E> NullAutoList<E> emptyAutoList() {
        return NullAutoList.getInstance();
    }

}
