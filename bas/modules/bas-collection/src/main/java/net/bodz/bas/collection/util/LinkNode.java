package net.bodz.bas.collection.util;

public interface LinkNode<N extends LinkNode<? extends N>> {

    N getPrev();

    N getNext();

}
