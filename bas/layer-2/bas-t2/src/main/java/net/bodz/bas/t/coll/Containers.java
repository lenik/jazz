package net.bodz.bas.t.coll;

public class Containers {

    public static <E> EmptyContainer<E> emptyArray() {
        return new EmptyContainer<E>(ContainerType.ARRAY);
    }

    public static <E> EmptyContainer<E> emptyList() {
        return new EmptyContainer<E>(ContainerType.LIST);
    }

    public static <E> EmptyContainer<E> emptySet() {
        return new EmptyContainer<E>(ContainerType.SET);
    }

    public static <E> EmptyContainer<E> emptyStringMap() {
        return new EmptyContainer<E>(ContainerType.STRING_MAP);
    }

}
