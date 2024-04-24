package net.bodz.bas.text.trie;

public class TokenTrie<T, node_t extends ITrieNode<String, T, node_t>>
        extends AbstractTrie<String, T, node_t> {

    public TokenTrie(INodeFactory<String, T, node_t> nodeFactory) {
        super(nodeFactory);
    }

}
