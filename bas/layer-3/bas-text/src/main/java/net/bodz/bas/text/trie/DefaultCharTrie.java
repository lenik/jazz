package net.bodz.bas.text.trie;

public class DefaultCharTrie<T>
        extends CharTrie<T, DefaultNode<Character, T>> {

    public DefaultCharTrie() {
        super((ITrie<Character, T, DefaultNode<Character, T>> trie, DefaultNode<Character, T> parent,
                Character key) -> new DefaultNode<>(trie, parent, key));
    }

}
