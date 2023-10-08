package net.bodz.bas.doc.word.xwpf;

import net.bodz.bas.t.stack.TypePredicate;
import net.bodz.bas.t.variant.IVariant;

/**
 * XWPF (XML Word Processor Format)
 *
 * It is used to read and write .docx extension files of MS-Word.
 */
public interface IXwpfNode {

    default XwpfDocNode getDocument() {
        return closest(XwpfPredicates.DOC);
    }

    IXwpfNode getParent();

    XwpfNodeType getType();

    default boolean isPars() {
        return false;
    }

    default boolean isPar() {
        return false;
    }

    default boolean isRun() {
        return false;
    }

    <R extends IXwpfNode> R closest(TypePredicate<IXwpfNode, R> predicate);

    default Object closestElement(TypePredicate<IXwpfNode, ? extends IXwpfNode> predicate) {
        IXwpfNode node = closest(predicate);
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
