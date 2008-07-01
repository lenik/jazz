package net.bodz.bas.types;

import java.util.List;

/**
 * List with ellipsis operators
 */
public interface Listl<E> extends List<E> {

    boolean addl(E... es);

    boolean removel(E... es);

    void removel(int... indexes) throws IndexOutOfBoundsException;

}
