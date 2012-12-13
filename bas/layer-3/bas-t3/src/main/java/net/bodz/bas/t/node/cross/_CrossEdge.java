package net.bodz.bas.t.node.cross;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.node.chain.ChainIteration;
import net.bodz.bas.t.node.chain.IChainNode;

public abstract class _CrossEdge
        implements ICrossEdge {

    @Override
    public final void setNext(IChainNode _next)
            throws ReadOnlyException {
        ICrossEdge next = (ICrossEdge) _next;
        setNext2(next);
    }

    @Override
    public final void setPrev(IChainNode _prev)
            throws ReadOnlyException {
        ICrossEdge prev = (ICrossEdge) _prev;
        setPrev2(prev);
    }

    protected void setNext2(ICrossEdge next)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    protected void setPrev2(ICrossEdge prev)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public void setIn(ICrossNode in)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public void setOut(ICrossNode out)
            throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public ICrossEdge getComing() {
        ICrossNode s = getIn();
        ICrossNode d = getOut();
        for (ICrossEdge dComing : ChainIteration.siblings(d.getIn()))
            if (dComing.getIn() == s)
                return dComing;
        return null;
    }

    @Override
    public ICrossEdge getGoing() {
        ICrossNode s = getIn();
        ICrossNode d = getOut();
        for (ICrossEdge sGoing : ChainIteration.siblings(s.getOut()))
            if (sGoing.getOut() == d)
                return sGoing;
        return null;
    }

}
