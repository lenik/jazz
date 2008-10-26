package net.bodz.graph.cross;

import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.graph.chain.IChainNode;

public interface ICrossEdge extends IChainNode {

    ICrossEdge getNext();

    ICrossEdge getPrev();

    /** assert next instanceof ICrossEdge */
    void setNext(IChainNode next) throws ReadOnlyException;

    /** assert prev instanceof ICrossEdge */
    void setPrev(IChainNode prev) throws ReadOnlyException;

    ICrossNode getOut();

    ICrossNode getIn();

    void setOut(ICrossNode out) throws ReadOnlyException;

    void setIn(ICrossNode in) throws ReadOnlyException;

    ICrossEdge getComing();

    ICrossEdge getGoing();

}
