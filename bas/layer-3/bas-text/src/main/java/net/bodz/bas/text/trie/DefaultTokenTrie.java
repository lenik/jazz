package net.bodz.bas.text.trie;

public class DefaultTokenTrie<T>
        extends TokenTrie<T, DefaultNode<String, T>> {

    public DefaultTokenTrie() {
        super((ITrie<String, T, DefaultNode<String, T>> trie, DefaultNode<String, T> parent,
                String key) -> new DefaultNode<>(trie, parent, key));
    }

}
