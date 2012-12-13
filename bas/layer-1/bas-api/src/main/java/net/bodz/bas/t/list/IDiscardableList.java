package net.bodz.bas.t.list;

import java.util.List;

public interface IDiscardableList<E>
        extends List<E> {

    boolean addAtPreferredIndex(int index, E element);

}
