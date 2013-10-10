package net.bodz.bas.t.node;

import net.bodz.bas.err.ReadOnlyException;

public class CChainNode
        extends _ChainNode {

    IChainNode next;
    IChainNode prev;

    public CChainNode(IChainNode next, IChainNode prev) {
        this.next = next;
        this.prev = prev;
    }

    @Override
    public IChainNode getNext() {
        return next;
    }

    @Override
    public IChainNode getPrev() {
        return prev;
    }

    @Override
    public void setNext(IChainNode next)
            throws ReadOnlyException {
        this.next = next;
    }

    @Override
    public void setPrev(IChainNode prev)
            throws ReadOnlyException {
        this.prev = prev;
    }

}
