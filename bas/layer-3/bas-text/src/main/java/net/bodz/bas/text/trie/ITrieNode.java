package net.bodz.bas.text.trie;

public interface ITrieNode<C, T> {

    ITrieNode<C, T> getParent();

    int getDepth();

    boolean isChild(C childKey);

    ITrieNode<C, T> getChild(C childKey);

    ITrieNode<C, T> getOrAddChild(C childKey);

    C getKey();

    boolean isDefined();

    void define(T data);

    T getData();

}