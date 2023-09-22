package net.bodz.bas.doc.word.xwpf;

import java.util.Stack;

import net.bodz.bas.t.variant.IVariant;

/**
 * XWPF: XML Word Processing Format
 */
public interface IXwpfNode {

    XwpfNodeType getType();

    IXwpfNode getParent();

    Object getElement();

    default IXwpfNode closestNode(XwpfNodeType nodeType) {
        IXwpfNode node = this;
        while (node != null) {
            if (node.getType() == nodeType)
                return node;
            node = node.getParent();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    default <T> T closestElement(XwpfNodeType nodeType) {
        IXwpfNode node = closestNode(nodeType);
        if (node == null)
            return null;
        else
            return (T) node.getElement();
    }

    default XwpfDocNode getClosestDocument() {
        return (XwpfDocNode) closestNode(XwpfNodeType.DOCUMENT);
    }

    default XwpfParNode getClosestParagraph() {
        return (XwpfParNode) closestNode(XwpfNodeType.PARAGRAPH);
    }

    default XwpfRunNode getClosestRun() {
        return (XwpfRunNode) closestNode(XwpfNodeType.RUN);
    }

    default XwpfTableNode getClosestTable() {
        return (XwpfTableNode) closestNode(XwpfNodeType.TABLE);
    }

    default XwpfTableRowNode getClosestTableRow() {
        return (XwpfTableRowNode) closestNode(XwpfNodeType.TABLE_ROW);
    }

    default XwpfTableCellNode getClosestTableCell() {
        return (XwpfTableCellNode) closestNode(XwpfNodeType.TABLE_CELL);
    }

    /**
     * @return <code>true</code> if handled.
     */
    default boolean attribute(String name, IVariant value) {
        return false;
    }

    Stack<TextStyle> getStyleStack();

}
