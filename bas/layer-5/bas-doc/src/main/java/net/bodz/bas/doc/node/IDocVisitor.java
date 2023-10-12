package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DepthVisitor;

public interface IDocVisitor {

    void document(Document doc);

    void partGroup(PartGroup partGroup);

    void part(Part part, int index);

    void list(ListPar list);

    // void listChild(IPar par, int index, int itemIndex);

    void listItem(ListItem item, int childIndex, int itemIndex);

    void table(Table table);

    void tableRow(TableRow row, int index);

    void tableCell(TableCell cell, int index);

    void textBox(TextBox textBox);

    void textPar(TextPar textPar);

    void textRun(TextRun textRun);

    void runGroup(RunGroup runGroup);

    void fontEnv(FontEnv fontEnv);

    void fontStyleEnv(FontStyleEnv fontStyleEnv);

    void hr(Hr hr);

    void image(Image image);

    default void havePars(IHavePars havePars) {
        for (IPar par : havePars.getPars())
            par.accept(this);
    }

    default void haveRuns(IHaveRuns haveRuns) {
        for (IRun run : haveRuns.getRuns())
            run.accept(this);
    }

    default IDocVisitor depth() {
        return new DepthVisitor(this);
    }

    default IDocVisitor depth(int maxDepth) {
        return new DepthVisitor(this, maxDepth);
    }

}
