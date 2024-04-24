package net.bodz.bas.text.trie;

public class DefaultNode<char_t, T>
        extends MutableTrieNode<char_t, T, DefaultNode<char_t, T>> {

    public DefaultNode(ITrie<char_t, T, DefaultNode<char_t, T>> trie, DefaultNode<char_t, T> parent, char_t key) {
        super(trie, parent, key);
    }

}
