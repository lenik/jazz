package net.bodz.bas.collection.preorder;

import java.util.Set;

public interface IPreorderSet<E>
        extends Set<E> {

    E meet(E e);

    Iterable<E> join(E e);

}
