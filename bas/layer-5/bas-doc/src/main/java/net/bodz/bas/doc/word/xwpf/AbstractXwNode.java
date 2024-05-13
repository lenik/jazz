package net.bodz.bas.doc.word.xwpf;

import java.util.Stack;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.stack.TypePredicate;

public abstract class AbstractXwNode
        implements
            IXwNode,
            IXmlForm {

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

    @Override
    public abstract void readObject(IElement element)
            throws ParseException, LoaderException;

    @Override
    public abstract void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException;

    @Override
    public String toString() {
        try {
            return XmlFn.toString(this);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
