package net.bodz.bas.doc.word.xwpf;

import net.bodz.bas.t.stack.TypePredicate;
import net.bodz.bas.t.variant.IVariant;

/**
 * XWPF (XML Word Processor Format)
 *
 * It is used to read and write .docx extension files of MS-Word.
 */
public interface IXwNode {

    default XwDocument getDocument() {
        return closest(XwPredicates.DOC);
    }

    IXwNode getParent();

    XwNodeType getType();

    /**
     * Whether the class implements {@link XwPar}.
     *
     * @return <code>true</code> if the class implements {@link XwPar}..
     */
    default boolean isPar() {
        return getType() == XwNodeType.PARAGRAPH;
    }

    /**
     * Whether the class implements {@link XwRun}.
     *
     * @return <code>true</code> if the class implements {@link XwRun}..
     */
    default boolean isRun() {
        return getType() == XwNodeType.RUN;
    }

    /**
     * Whether the class implements {@link IXwHavePars}.
     *
     * @return <code>true</code> if the class implements {@link IXwHavePars}..
     */
    default boolean havePars() {
        return false;
    }

    /**
     * Whether the class implements {@link IXwHaveRuns}.
     *
     * @return <code>true</code> if the class implements {@link IXwHaveRuns}..
     */
    default boolean haveRuns() {
        return false;
    }

    <R extends IXwNode> R closest(TypePredicate<IXwNode, R> predicate);

    default Object closestElement(TypePredicate<IXwNode, ? extends IXwNode> predicate) {
        IXwNode node = closest(predicate);
        return node == null ? null : node.getElement();
    }

    Object getElement();

    /**
     * @return <code>true</code> if handled.
     */
    default boolean attribute(String name, IVariant value) {
        return false;
    }

//    Stack<TextStyle> getStyleStack();

}
