package net.bodz.bas.doc.word.xwpf;

public interface IXwHavePars
        extends
            IXwNode {

    @Override
    default boolean havePars() {
        return true;
    }

    XwPar addPar();

    XwPar getParToAppend();

    void addPlainText(String text);

}
