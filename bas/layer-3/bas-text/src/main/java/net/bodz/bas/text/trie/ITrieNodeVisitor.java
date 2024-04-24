package net.bodz.bas.text.trie;

@FunctionalInterface
public interface ITrieNodeVisitor<node_t> {

    boolean node(node_t node);

    default boolean leafNode(node_t node) {
        return true;
    }

}
