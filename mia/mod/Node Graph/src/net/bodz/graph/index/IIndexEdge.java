package net.bodz.graph.index;

public interface IIndexEdge {

    IIndexNode getIn();

    IIndexNode getOut();

    void setIn(IIndexNode in);

    void setOut(IIndexNode out);

}
