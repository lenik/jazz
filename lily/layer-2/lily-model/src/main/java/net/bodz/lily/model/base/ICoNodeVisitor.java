package net.bodz.lily.model.base;

public interface ICoNodeVisitor<node_t extends CoNode<?, ?>> {

    void node(node_t node);

}
