package net.bodz.bas.text.trie;

import java.util.List;

public interface ITrie<C, T> {

    Node<C, T> getRoot();

    Node<C, T> resolve(Iterable<C> seq);

    /**
     * @return heads
     */
    int[] scanTries(List<C> content);

    interface Node<C, T> {

        boolean isDefined();

        void define(T data);

        boolean isChild(C childKey);

        Node<C, T> getChild(C childKey);

        Node<C, T> getOrAddChild(C childKey);

        T getData();

    }

}
