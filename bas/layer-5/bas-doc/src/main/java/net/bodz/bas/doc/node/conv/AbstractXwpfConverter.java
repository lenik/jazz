package net.bodz.bas.doc.node.conv;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.doc.node.util.FullDocVisitor;
import net.bodz.bas.doc.word.DocNums;
import net.bodz.bas.doc.word.INumStyles;
import net.bodz.bas.doc.word.xwpf.IXwNode;
import net.bodz.bas.doc.word.xwpf.XwPredicates;

public abstract class AbstractXwpfConverter
        extends FullDocVisitor
        implements
            INumStyles {

    protected final XWPFDocument _document;
    protected IXwNode x_ptr;

    DocNums nums;
//    DocNum bullets1;
//    DocNum decimals1;

    protected static class xp
            extends XwPredicates {
    }

    public AbstractXwpfConverter(XWPFDocument _document) {
        if (_document == null)
            throw new NullPointerException("_document");
        this._document = _document;

        nums = new DocNums(_document);
//        bullets1 = nums.addNum(BULLETS);
//        decimals1 = nums.addNum(DECIMALS);
    }

    protected final void scope(Supplier<IXwNode> next, INode... visitNodes) {
        scope(next, null, visitNodes);
    }

    protected synchronized <node_t extends IXwNode> //
    void scope(Supplier<node_t> next, Consumer<node_t> consumer, INode... visitNodes) {
        IXwNode bak = x_ptr;
        node_t node = next.get();
        x_ptr = node;
        if (consumer != null)
            consumer.accept(node);
        for (INode n : visitNodes) {
            n.internalAccept(this);
        }
        x_ptr = bak;
    }

    protected <xw_t extends IXwNode, node_t extends INode> //
    void scopeX(IXwNodeBuilder2<xw_t, node_t> builder) {
        scopeX(builder, null);
    }

    protected synchronized <xw_t extends IXwNode, node_t extends INode> //
    void scopeX(IXwNodeBuilder2<xw_t, node_t> builder, node_t source, INode... visitNodes) {
        IXwNode bak = x_ptr;
        xw_t child = builder.createXwNode(x_ptr);
        x_ptr = child;
        builder.prepare(child, source);

        if (source != null)
            source.internalAccept(this);

        for (INode n : visitNodes) {
            n.internalAccept(this);
        }
        x_ptr = bak;
    }

    protected synchronized //
    void scope2(Function<IXwNode, IXwNode> next, INode... visitNodes) {
        IXwNode bak = x_ptr;
        x_ptr = next.apply(x_ptr);
        for (INode node : visitNodes) {
            node.internalAccept(this);
        }
        x_ptr = bak;
    }

}