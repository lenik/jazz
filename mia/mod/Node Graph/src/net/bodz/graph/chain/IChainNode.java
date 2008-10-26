package net.bodz.graph.chain;

import net.bodz.bas.lang.err.ReadOnlyException;

public interface IChainNode {

    IChainNode getNext();

    IChainNode getPrev();

    void setNext(IChainNode next) throws ReadOnlyException;

    void setPrev(IChainNode prev) throws ReadOnlyException;

}
