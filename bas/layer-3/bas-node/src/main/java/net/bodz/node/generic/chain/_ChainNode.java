package net.bodz.node.generic.chain;

import net.bodz.bas.err.ReadOnlyException;

public abstract class _ChainNode
        implements IChainNode {

    @Override
    public void setNext(IChainNode next)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public void setPrev(IChainNode prev)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

}
