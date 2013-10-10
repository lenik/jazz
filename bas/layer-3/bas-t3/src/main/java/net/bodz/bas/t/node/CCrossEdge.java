package net.bodz.bas.t.node;

import net.bodz.bas.err.ReadOnlyException;

public class CCrossEdge
        extends _CrossEdge {

    ICrossNode in;
    ICrossNode out;

    ICrossEdge next;
    ICrossEdge prev;

    @Override
    public ICrossNode getIn() {
        return in;
    }

    @Override
    public ICrossNode getOut() {
        return out;
    }

    @Override
    public void setIn(ICrossNode in) {
        this.in = in;
    }

    @Override
    public void setOut(ICrossNode out) {
        this.out = out;
    }

    @Override
    public ICrossEdge getNext() {
        return next;
    }

    @Override
    public ICrossEdge getPrev() {
        return prev;
    }

    @Override
    protected void setNext2(ICrossEdge next)
            throws ReadOnlyException {
        this.next = next;
    }

    @Override
    protected void setPrev2(ICrossEdge prev)
            throws ReadOnlyException {
        this.prev = prev;
    }

}
