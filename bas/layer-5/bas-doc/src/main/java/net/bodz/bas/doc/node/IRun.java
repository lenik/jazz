package net.bodz.bas.doc.node;

public interface IRun
        extends
            INode {

    default boolean isTextRun() {
        return false;
    }

}
