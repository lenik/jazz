package net.bodz.bas.doc.word.xwpf;

import java.util.Stack;

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
    public synchronized Stack<TextStyle> getStyleStack() {
        if (styleStack == null)
            styleStack = new Stack<>();
        return styleStack;
    }

    public TextStyle beginStyle() {
        Stack<TextStyle> stack = getStyleStack();
        TextStyle top = stack.isEmpty() ? null : stack.lastElement();
        TextStyle style;
        if (top == null)
            style = new TextStyle();
        else
            style = new TextStyle(top); // clone
        stack.push(style);
        return style;
    }

    public TextStyle endStyle() {
        Stack<TextStyle> stack = getStyleStack();
        if (stack.isEmpty())
            throw new IllegalStateException("stack underflow");
        TextStyle top = stack.pop();
        return top;
    }

}
