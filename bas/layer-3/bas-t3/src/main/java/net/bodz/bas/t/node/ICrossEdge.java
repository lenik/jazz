package net.bodz.bas.t.node;

import net.bodz.bas.err.ReadOnlyException;

public interface ICrossEdge
        extends IChainNode {

    ICrossEdge getNext();

    ICrossEdge getPrev();

    /** assert next instanceof ICrossEdge */
    void setNext(IChainNode next)
            throws ReadOnlyException;

    /** assert prev instanceof ICrossEdge */
    void setPrev(IChainNode prev)
            throws ReadOnlyException;

    ICrossNode getOut();

    ICrossNode getIn();

    void setOut(ICrossNode out)
            throws ReadOnlyException;

    void setIn(ICrossNode in)
            throws ReadOnlyException;

    ICrossEdge getComing();

    ICrossEdge getGoing();

}
