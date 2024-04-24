package net.bodz.bas.text.trie;

@FunctionalInterface
public interface ILeafNodeVisitor<char_t, T, node_t extends ITrieNode<char_t, T, node_t>>
        extends
            ITrieNodeVisitor<node_t> {

    @Override
    default boolean node(node_t node) {
        for (char_t childKey : node.getChildKeys()) {
            node_t child = node.getChild(childKey);
            if (! child.accept(this))
                return false;
        }
        return true;
    }

    @Override
    boolean leafNode(node_t node);

}
