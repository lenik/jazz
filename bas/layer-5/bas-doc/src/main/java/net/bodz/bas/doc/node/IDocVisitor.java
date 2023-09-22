package net.bodz.bas.doc.node;

public interface IDocVisitor {

    default void beginNode(IDocNode node) {
    }

    default void endNode(IDocNode node) {
    }

    default void attribute(String name, Object value) {
    }

    default void par(IDocPar par) {
    }

    default void run(IDocRun run) {
    }

    default void document(DocDocument doc) {
    }

    default void hr(DocHr hr) {
    }

    default void image(DocImage image) {
    }

    default void section(DocSection section) {
    }

    default void list(DocListPar list) {
    }

    default void listItem(IDocPar item) {
    }

    default void textBox(DocTextBox textBox) {
    }

    default void table(DocTable table) {
    }

    default void tableRow(DocTableRow row) {
    }

    default void tableCell(DocTableCell cell) {
    }

    default void fontEnv(FontEnv fontEnv) {
    }

    default void fontStyleEnv(FontStyleEnv fontStyleEnv) {
    }

    default void textPar(DocTextPar textPar) {
    }

    default void textRun(DocTextRun textRun) {
    }

    default void chars(String s) {
    }

}
