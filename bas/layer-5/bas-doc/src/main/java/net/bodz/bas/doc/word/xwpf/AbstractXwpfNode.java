package net.bodz.bas.doc.word.xwpf;

import java.util.Stack;

import net.bodz.bas.t.stack.TypePredicate;

public abstract class AbstractXwpfNode
        implements
            IXwpfNode {

    final IXwpfNode parent;
    Stack<TextStyle> styleStack;

    public AbstractXwpfNode() {
        this(null);
    }

    public AbstractXwpfNode(IXwpfNode parent) {
        this.parent = parent;
    }

    @Override
    public IXwpfNode getParent() {
        return parent;
    }

    @Override
    public <R extends IXwpfNode> //
    R closest(TypePredicate<IXwpfNode, R> predicate) {
        if (predicate == null)
            throw new NullPointerException("predicate");
        IXwpfNode node = this;
        while (node != null) {
            if (predicate.test(node)) {
                @SuppressWarnings("unchecked")
                R typed = (R) node;
                return typed;
            }
            node = node.getParent();
        }
        return null;
    }

}
