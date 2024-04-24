package net.bodz.bas.text.trie;

import java.util.Set;

public interface ITrieNode<char_t, T, self_t> {

    self_t getParent();

    int getDepth();

    boolean isChild(char_t childKey);

    Set<char_t> getChildKeys();

    self_t getChild(char_t childKey);

    self_t getOrAddChild(char_t childKey);

    self_t removeChild(char_t childKey);

    boolean isEmpty();

    boolean isNotEmpty();

    self_t detach();

    self_t detach(boolean detachEmptyAncestors);

    char_t getKey();

    boolean isWildcard();

    boolean isDefined();

    T getData();

    T setData(T data);

    void addData(T data);

    boolean removeData();

    void clear();

    boolean accept(ITrieNodeVisitor<self_t> visitor);

}