package net.bodz.bas.t.coll;

public enum ContainerType {

    SINGLE, // Single&Error

    ONCE, // Single&Ignored

    REPLACED, // Single&Replaced

    ARRAY,
    // ARRAY_RO,

    LIST,

    SET,

    STRING_MAP,

    OTHER,

    ;

    public <E> IContainer<E> create(Class<? extends E> type) {
        switch (this) {
        case SINGLE:
            return new SingleContainer<E>(type, OverflowAction.ERROR);
        case ONCE:
            return new SingleContainer<E>(type, OverflowAction.IGNORE);
        case REPLACED:
            return new SingleContainer<E>(type, OverflowAction.REPLACE);
        case ARRAY:
            return new ArrayContainer<E>(type);
//        case ARRAY_RO:
//            return new ArrayContainer<E>(type);
        case LIST:
            return new ListContainer<E>(type);
        case SET:
            return new SetContainer<E>(type);
        case STRING_MAP:
            return new StringMapContainer<E>(type);
        default:
            throw new UnsupportedOperationException();
        }
    }

}
