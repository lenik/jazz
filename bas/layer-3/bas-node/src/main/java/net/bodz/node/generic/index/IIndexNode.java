package net.bodz.node.generic.index;

import net.bodz.bas.err.ReadOnlyException;

public interface IIndexNode {

    int getOutSize();

    IIndexNode getOut(int index);

    void addOut(IIndexNode node)
            throws ReadOnlyException;

    void addOut(int index, IIndexNode node)
            throws ReadOnlyException;

    void removeOut(int index)
            throws ReadOnlyException;

    int getInSize();

    IIndexNode getIn(int index);

    void addIn(IIndexNode node)
            throws ReadOnlyException;

    void addIn(int index, IIndexNode node)
            throws ReadOnlyException;

    void removeIn(int index)
            throws ReadOnlyException;

}
