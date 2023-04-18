package net.bodz.lily.model.base;

public interface ICoNodeVisitor<node_t extends CoNode<?, ?>> {

    /**
     * @return <code>true</code> to include children and the remaining siblings.
     */
    boolean beginNode(node_t node);

    /**
     * @return <code>true</code> to visit children nodes, <code>false</code> to ignore children.
     */
    default boolean beginChildren(node_t node) {
        return true;
    }

    default void endChildren(node_t node) {
    }

    /**
     * @return <code>true</code> to include remaining siblings.
     */
    default boolean endNode(node_t node) {
        return true;
    }

}
