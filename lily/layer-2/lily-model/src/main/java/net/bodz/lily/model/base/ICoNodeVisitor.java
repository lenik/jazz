package net.bodz.lily.model.base;

public interface ICoNodeVisitor<node_t extends CoNode<?, ?>> {

    boolean begin(node_t node);

    void end(node_t node);

}
