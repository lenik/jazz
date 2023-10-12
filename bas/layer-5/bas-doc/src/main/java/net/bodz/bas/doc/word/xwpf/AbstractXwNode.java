package net.bodz.bas.doc.word.xwpf;

import java.util.Stack;

import net.bodz.bas.t.stack.TypePredicate;

public abstract class AbstractXwNode
        implements
            IXwNode {

    final IXwNode parent;
    Stack<TextStyle> styleStack;

    public AbstractXwNode() {
        this(null);
    }

    public AbstractXwNode(IXwNode parent) {
        this.parent = parent;
    }

    @Override
    public IXwNode getParent() {
        return parent;
    }

    @Override
    public <R extends IXwNode> //
    R closest(TypePredicate<IXwNode, R> predicate) {
        if (predicate == null)
            throw new NullPointerException("predicate");
        IXwNode node = this;
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
