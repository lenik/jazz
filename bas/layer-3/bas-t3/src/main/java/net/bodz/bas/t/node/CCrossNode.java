package net.bodz.bas.t.node;

import net.bodz.bas.err.ReadOnlyException;

public class CCrossNode
        extends _CrossNode {

    ICrossNode next;
    ICrossNode prev;

    ICrossEdge in;
    ICrossEdge out;

    @Override
    public ICrossNode getNext() {
        return next;
    }

    @Override
    public ICrossNode getPrev() {
        return prev;
    }

    @Override
    protected void setNext2(ICrossNode next)
            throws ReadOnlyException {
        this.next = next;
    }

    @Override
    protected void setPrev2(ICrossNode prev)
            throws ReadOnlyException {
        this.prev = prev;
    }

    @Override
    public ICrossEdge getIn() {
        return in;
    }

    @Override
    public ICrossEdge getOut() {
        return out;
    }

    @Override
    public void setIn(ICrossEdge in)
            throws ReadOnlyException {
        this.in = in;
    }

    @Override
    public void setOut(ICrossEdge out)
            throws ReadOnlyException {
        this.out = out;
    }

}
