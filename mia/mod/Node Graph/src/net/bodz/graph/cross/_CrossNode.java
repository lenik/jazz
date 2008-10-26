package net.bodz.graph.cross;

import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.graph.chain.IChainNode;

public abstract class _CrossNode implements ICrossNode {

    @Override
    public void setNext(IChainNode _next) throws ReadOnlyException {
        ICrossNode next = (ICrossNode) _next;
        setNext2(next);
    }

    @Override
    public void setPrev(IChainNode _prev) throws ReadOnlyException {
        ICrossNode prev = (ICrossNode) _prev;
        setPrev2(prev);
    }

    protected void setNext2(ICrossNode next) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    protected void setPrev2(ICrossNode prev) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public void setIn(ICrossEdge in) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public void setOut(ICrossEdge out) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

}
