package net.bodz.bas.doc.node;

import java.util.List;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.t.list.IListEx;

public interface IDocNode
        extends
            IAttributed {

    IDocNode getParent();

    default boolean isRoot() {
        return getParent() == null;
    }

    default boolean isDocument() {
        return false;
    }

    default DocDocument getDocument() {
        IDocNode parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getDocument();
    }

    /**
     * @return 1 for leaf node.
     */
    default int getDepth() {
        int depth = 0;
        IDocNode node = this;
        while (node != null) {
            depth++;
            node = node.getParent();
        }
        return depth;
    }

    default <T extends IDocNode> T closest(Class<T> nodeType) {
        IDocNode node = this;
        while (node != null) {
            if (nodeType.isInstance(node))
                return nodeType.cast(node);
            node = node.getParent();
        }
        return null;
    }

    IListEx<? extends IDocNode> getChildren();

    default int getChildCount() {
        List<?> children = getChildren();
        return children == null ? 0 : children.size();
    }

    default IDocNode getChild(int index) {
        List<? extends IDocNode> children = getChildren();
        return children == null ? null : children.get(index);
    }

    default boolean isPar() {
        return false;
    }

    default boolean isRun() {
        return false;
    }

    default boolean havePars() {
        return false;
    }

    default boolean haveRuns() {
        return false;
    }

    void buildText(StringBuilder a);

    default String getText() {
        StringBuilder a = new StringBuilder();
        buildText(a);
        return a.toString();
    }

    void setText(String s);

    void accept(IDocVisitor visitor);

    void internalAccept(IDocVisitor visitor);

    default void dump(ICharOut out) {
        dump(TreeOutImpl.from(out));
    }

    default void dump(ITreeOut out) {
        DocNodeDumper dumper = new DocNodeDumper(out);
        accept(dumper);
    }

}
