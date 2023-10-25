package net.bodz.bas.doc.node;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.doc.node.util.DocNodeDumper;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.t.list.IListEx;
import net.bodz.bas.t.stack.TypePredicate;

public interface INode
        extends
            IAttributed {

    INode getParent();

    default boolean isRoot() {
        return getParent() == null;
    }

    NodeType getType();

    default Document getDocument() {
        INode parent = getParent();
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
        INode node = this;
        while (node != null) {
            depth++;
            node = node.getParent();
        }
        return depth;
    }

    default <T extends INode> T closest(Class<T> nodeType) {
        INode node = this;
        while (node != null) {
            if (nodeType.isInstance(node))
                return nodeType.cast(node);
            node = node.getParent();
        }
        return null;
    }

    default <T> T closest(TypePredicate<INode, T> predicate) {
        INode node = this;
        while (node != null) {
            if (predicate.test(node)) {
                @SuppressWarnings("unchecked")
                T typed = (T) node;
                return typed;
            }
            node = node.getParent();
        }
        return null;
    }

    /**
     * @return ancestor nodes from leaf to root.
     */
    default <T> List<T> ancestors(TypePredicate<INode, T> predicate) {
        List<T> list = new ArrayList<>();
        INode node = this;
        while (node != null) {
            if (predicate.test(node)) {
                @SuppressWarnings("unchecked")
                T typed = (T) node;
                list.add(typed);
            }
            node = node.getParent();
        }
        return list;
    }

    IListEx<? extends INode> getChildren();

    default int getChildCount() {
        List<?> children = getChildren();
        return children == null ? 0 : children.size();
    }

    default INode getChild(int index) {
        List<? extends INode> children = getChildren();
        return children == null ? null : children.get(index);
    }

    default int indexOfChild(INode child) {
        List<? extends INode> children = getChildren();
        return children == null ? -1 : children.indexOf(child);
    }

    default int getChildIndex() {
        INode parent = getParent();
        return parent.indexOfChild(this);
    }

    /**
     * Whether the class implements {@link IPar}.
     *
     * @return <code>true</code> if the class implements {@link IPar}..
     */
    default boolean isPar() {
        return false;
    }

    /**
     * Whether the class implements {@link IRun}.
     *
     * @return <code>true</code> if the class implements {@link IRun}..
     */
    default boolean isRun() {
        return false;
    }

    /**
     * Whether the class implements {@link IHavePars}.
     *
     * @return <code>true</code> if the class implements {@link IHavePars}..
     */
    default boolean havePars() {
        return false;
    }

    /**
     * Whether the class implements {@link IHaveRuns}.
     *
     * @return <code>true</code> if the class implements {@link IHaveRuns}..
     */
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

    default boolean canReduce() {
        return false;
    }

    void accept(IDocVisitor visitor);

    default void dump(ICharOut out) {
        dump(TreeOutImpl.from(out));
    }

    default void dump(ITreeOut out) {
        DocNodeDumper dumper = new DocNodeDumper(out);
        accept(dumper);
    }

}
