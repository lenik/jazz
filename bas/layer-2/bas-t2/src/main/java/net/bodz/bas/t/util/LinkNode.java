package net.bodz.bas.t.util;

public interface LinkNode<N extends LinkNode<? extends N>> {

    N getPrev();

    N getNext();

}
