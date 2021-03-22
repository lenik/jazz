package net.bodz.bas.text.trie;

import java.util.List;

public interface ITrie<C, T> {

    ITrieNode<C, T> getRoot();

    ITrieNode<C, T> resolve(Iterable<C> seq);

    ITrieNode<C, T> findOverlap(Iterable<C> seq);

    /**
     * @return heads
     */
    int[] scanTries(List<C> content);

}
