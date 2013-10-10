package net.bodz.bas.t.node;

import net.bodz.bas.err.ReadOnlyException;

public interface ICrossNode
        extends IChainNode {

    ICrossNode getNext();

    ICrossNode getPrev();

    void setNext(IChainNode next)
            throws ReadOnlyException;

    void setPrev(IChainNode prev)
            throws ReadOnlyException;

    ICrossEdge getOut();

    ICrossEdge getIn();

    void setOut(ICrossEdge out)
            throws ReadOnlyException;

    void setIn(ICrossEdge in)
            throws ReadOnlyException;

}
