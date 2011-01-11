package net.bodz.bas.annotation.util.impl;

import java.util.List;

public interface IDiscardableList<E>
        extends List<E> {

    boolean addAtPreferredIndex(int index, E element);

}
