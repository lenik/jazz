package net.bodz.bas.types;

public interface LinkNode<N extends LinkNode<? extends N>> {

    N getPrev();

    N getNext();

}
