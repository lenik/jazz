package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.conv.MarkdownConverter;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;

public class Document
        extends AbstractParGroup {

    public final TextPar title = new TextPar(this);

    public Document() {
        super(null);
    }

    @Override
    public NodeType getType() {
        return NodeType.DOCUMENT;
    }

    @Override
    public Document getDocument() {
        return this;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.document(this);
    }

    public String toMarkdown() {
        BCharOut buf = new BCharOut();
        ITreeOut out = buf.indented();
        out.getTextIndention().setIndentSize(4);
        MarkdownConverter conv = new MarkdownConverter(out);
        accept(conv);
        return buf.toString();
    }

}
