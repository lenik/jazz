package net.bodz.bas.t.specmap;

public interface ISpecNodeVisitor<node_t extends ISpecNode<node_t, key_t, val_t>, key_t, val_t>
        extends
            ISpecMapVisitor<key_t, node_t> {

    default void visitNodeValue(val_t value) {
    }

}
