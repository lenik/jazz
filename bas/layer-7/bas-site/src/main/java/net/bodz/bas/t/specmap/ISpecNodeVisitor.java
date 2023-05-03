package net.bodz.bas.t.specmap;

public interface ISpecNodeVisitor<node_t extends ISpecNode<node_t, key_t, val_t>, key_t, val_t>
        extends
            ISpecMapVisitor<key_t, node_t> {

    default boolean isPruneEmptyNode() {
        return false;
    }

    default boolean beginNode(node_t node) {
        return true;
    }

    default void visitNodeValue(val_t value) {
    }

    default void endNode(node_t node) {
    }

}
