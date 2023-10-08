package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.node.*;

public abstract class FullDocVisitor
        implements
            IDocVisitor {

    @Override
    public void document(Document doc) {
        doc.internalAccept(this);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        partGroup.internalAccept(this);
    }

    @Override
    public void part(Part part, int index) {
        part.internalAccept(this);
    }

    @Override
    public void list(ListPar list) {
        list.internalAccept(this);
    }

    @Override
    public void listItem(IPar par, int index, int itemIndex) {
        par.internalAccept(this);
    }

    @Override
    public void textBox(TextBox textBox) {
        textBox.internalAccept(this);
    }

    @Override
    public void table(Table table) {
        table.internalAccept(this);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        row.internalAccept(this);
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        cell.internalAccept(this);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        fontEnv.internalAccept(this);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        fontStyleEnv.internalAccept(this);
    }

    @Override
    public void textPar(TextPar textPar) {
        textPar.internalAccept(this);
    }

    @Override
    public void textRun(TextRun textRun) {
        textRun.internalAccept(this);
    }

}
