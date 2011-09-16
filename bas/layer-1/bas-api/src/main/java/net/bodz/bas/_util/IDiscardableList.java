package net.bodz.bas._util;

import java.util.List;

public interface IDiscardableList<E>
        extends List<E> {

    boolean addAtPreferredIndex(int index, E element);

}
