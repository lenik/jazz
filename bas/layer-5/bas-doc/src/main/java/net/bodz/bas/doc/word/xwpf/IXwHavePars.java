package net.bodz.bas.doc.word.xwpf;

public interface IXwHavePars
        extends
            IXwNode {

    @Override
    default boolean havePars() {
        return true;
    }

    XwPar addPar();

    void addPlainText(String text);

}
