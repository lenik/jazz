package net.bodz.bas.t.node.chain;

import net.bodz.bas.err.ReadOnlyException;

public interface IChainNode {

    IChainNode getNext();

    IChainNode getPrev();

    void setNext(IChainNode next)
            throws ReadOnlyException;

    void setPrev(IChainNode prev)
            throws ReadOnlyException;

}
