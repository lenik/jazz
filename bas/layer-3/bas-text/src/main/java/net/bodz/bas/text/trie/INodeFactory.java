package net.bodz.bas.text.trie;

@FunctionalInterface
public interface INodeFactory<char_t, T, node_t extends ITrieNode<char_t, T, node_t>> {

    node_t create(ITrie<char_t, T, node_t> trie, node_t parent, char_t key);

}
