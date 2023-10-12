package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.node.*;

public class DepthVisitor
        extends AbstractDocVisitor {

    IDocVisitor handler;
    IDocVisitor outer;

    public DepthVisitor(IDocVisitor handler) {
        this(handler, UNLIMIT, null);
    }

    public DepthVisitor(IDocVisitor handler, int maxDepth) {
        this(handler, maxDepth, null);
    }

    public DepthVisitor(IDocVisitor handler, IDocVisitor outer) {
        this(handler, UNLIMIT, outer);
    }

    public DepthVisitor(IDocVisitor handler, int maxDepth, IDocVisitor outer) {
        super(maxDepth);
        if (handler == null)
            throw new NullPointerException("handler");
        this.handler = handler;
        this.outer = outer != null ? outer : this;
    }

    @Override
    protected IDocVisitor getNextVisitor() {
        return outer;
    }

    @Override
    public void document(Document doc) {
        handler.document(doc);
        super.document(doc);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        handler.partGroup(partGroup);
        super.partGroup(partGroup);
    }

    @Override
    public void part(Part part, int index) {
        handler.part(part, index);
        super.part(part, index);
    }

    @Override
    public void list(ListPar list) {
        handler.list(list);
        super.list(list);
    }

    @Override
    public void listItem(ListItem item, int childIndex, int itemIndex) {
        handler.listItem(item, childIndex, itemIndex);
        super.listItem(item, childIndex, itemIndex);
    }

    @Override
    public void table(Table table) {
        handler.table(table);
        super.table(table);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        handler.tableRow(row, index);
        super.tableRow(row, index);
    }

    @Override
    public void textBox(TextBox textBox) {
        handler.textBox(textBox);
        super.textBox(textBox);
    }

    @Override
    public void textPar(TextPar textPar) {
        handler.textPar(textPar);
        super.textPar(textPar);
    }

    @Override
    public void textRun(TextRun textRun) {
        handler.textRun(textRun);
        super.textRun(textRun);
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        handler.runGroup(runGroup);
        super.runGroup(runGroup);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        handler.fontEnv(fontEnv);
        super.fontEnv(fontEnv);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        handler.fontStyleEnv(fontStyleEnv);
        super.fontStyleEnv(fontStyleEnv);
    }

    @Override
    public void hr(Hr hr) {
        handler.hr(hr);
        super.hr(hr);
    }

    @Override
    public void image(Image image) {
        handler.image(image);
        super.image(image);
    }

}
