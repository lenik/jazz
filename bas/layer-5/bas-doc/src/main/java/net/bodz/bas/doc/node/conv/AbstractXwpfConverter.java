package net.bodz.bas.doc.node.conv;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.doc.node.util.FullDocVisitor;
import net.bodz.bas.doc.word.xwpf.IXwpfNode;
import net.bodz.bas.doc.word.xwpf.XwpfPredicates;

public abstract class AbstractXwpfConverter
        extends FullDocVisitor {

    protected final XWPFDocument _document;
    protected IXwpfNode n_ptr;

    protected static class xp
            extends XwpfPredicates {
    }

    public AbstractXwpfConverter(XWPFDocument _document) {
        if (_document == null)
            throw new NullPointerException("_document");
        this._document = _document;
    }

    protected final void scope(Supplier<IXwpfNode> next, INode... visitNodes) {
        scope(next, null, visitNodes);
    }

    protected synchronized <node_t extends IXwpfNode> //
    void scope(Supplier<node_t> next, Consumer<node_t> consumer, INode... visitNodes) {
        IXwpfNode bak = n_ptr;
        node_t node = next.get();
        n_ptr = node;
        if (consumer != null)
            consumer.accept(node);
        for (INode n : visitNodes) {
            n.internalAccept(this);
        }
        n_ptr = bak;
    }

    protected synchronized //
    void scope2(Function<IXwpfNode, IXwpfNode> next, INode... visitNodes) {
        IXwpfNode bak = n_ptr;
        n_ptr = next.apply(n_ptr);
        for (INode node : visitNodes) {
            node.internalAccept(this);
        }
        n_ptr = bak;
    }

}