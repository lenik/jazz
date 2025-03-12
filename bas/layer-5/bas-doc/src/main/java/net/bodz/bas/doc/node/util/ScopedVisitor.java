package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.FontEnv;
import net.bodz.bas.doc.node.FontStyleEnv;
import net.bodz.bas.doc.node.Hr;
import net.bodz.bas.doc.node.IDocVisitor;
import net.bodz.bas.doc.node.INode;
import net.bodz.bas.doc.node.Image;
import net.bodz.bas.doc.node.ListItem;
import net.bodz.bas.doc.node.ListPar;
import net.bodz.bas.doc.node.Part;
import net.bodz.bas.doc.node.PartGroup;
import net.bodz.bas.doc.node.Table;
import net.bodz.bas.doc.node.TableCell;
import net.bodz.bas.doc.node.TableRow;
import net.bodz.bas.doc.node.TextBox;
import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.node.TextRun;

public abstract class ScopedVisitor
        implements
            IDocVisitor {

    IDocVisitor next;

    public ScopedVisitor(IDocVisitor next) {
        if (next == null)
            throw new NullPointerException("next");
        this.next = next;
    }

    protected abstract void beginNode(INode node);

    protected abstract void endNode(INode node);

    @Override
    public void document(Document doc) {
        beginNode(doc);
        next.document(doc);
        endNode(doc);
    }

    @Override
    public void hr(Hr hr) {
        beginNode(hr);
        next.hr(hr);
        endNode(hr);
    }

    @Override
    public void image(Image image) {
        beginNode(image);
        next.image(image);
        endNode(image);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        beginNode(partGroup);
        next.partGroup(partGroup);
        endNode(partGroup);
    }

    @Override
    public void part(Part part, int index) {
        beginNode(part);
        next.part(part, index);
        endNode(part);
    }

    @Override
    public void list(ListPar list) {
        beginNode(list);
        next.list(list);
        endNode(list);
    }

    @Override
    public void listItem(ListItem item, int childIndex, int itemIndex) {
        beginNode(item);
        listItem(item, childIndex, itemIndex);
        endNode(item);
    }

    @Override
    public void textBox(TextBox textBox) {
        beginNode(textBox);
        next.textBox(textBox);
        endNode(textBox);
    }

    @Override
    public void table(Table table) {
        beginNode(table);
        next.table(table);
        endNode(table);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        beginNode(row);
        next.tableRow(row, index);
        endNode(row);
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        beginNode(cell);
        next.tableCell(cell, index);
        endNode(cell);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        beginNode(fontEnv);
        next.fontEnv(fontEnv);
        endNode(fontEnv);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        beginNode(fontStyleEnv);
        next.fontStyleEnv(fontStyleEnv);
        endNode(fontStyleEnv);
    }

    @Override
    public void textPar(TextPar textPar) {
        beginNode(textPar);
        next.textPar(textPar);
        endNode(textPar);
    }

    @Override
    public void textRun(TextRun textRun) {
        beginNode(textRun);
        next.textRun(textRun);
        endNode(textRun);
    }

}
