package net.bodz.bas.doc.word.xwpf;

public interface IXwHaveRuns
        extends
            IXwNode {

    @Override
    default boolean haveRuns() {
        return true;
    }

    XwRun addRun();

    void addPlainText(String text);

}
