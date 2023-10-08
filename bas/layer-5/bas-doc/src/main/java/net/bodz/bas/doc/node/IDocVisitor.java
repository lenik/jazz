package net.bodz.bas.doc.node;

public interface IDocVisitor {

    default void beginNode(INode node) {
    }

    default void endNode(INode node) {
    }

    default void attribute(String name, Object value) {
    }

    default void property(String name, Object value) {
    }

    default void document(Document doc) {
    }

    default void hr(Hr hr) {
    }

    default void image(Image image) {
    }

    default void partGroup(PartGroup partGroup) {
    }

    default void part(Part part, int index) {
    }

    default void list(ListPar list) {
    }

    default void listItem(IPar par, int index, int itemIndex) {
    }

    default void textBox(TextBox textBox) {
    }

    default void table(Table table) {
    }

    default void tableRow(TableRow row, int index) {
    }

    default void tableCell(TableCell cell, int index) {
    }

    default void fontEnv(FontEnv fontEnv) {
    }

    default void fontStyleEnv(FontStyleEnv fontStyleEnv) {
    }

    default void textPar(TextPar textPar) {
    }

    default void textRun(TextRun textRun) {
    }

    default void chars(String s) {
    }

}
