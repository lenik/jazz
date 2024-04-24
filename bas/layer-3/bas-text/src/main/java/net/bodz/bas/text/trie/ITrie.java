package net.bodz.bas.text.trie;

import net.bodz.bas.t.vector.Vector;

public interface ITrie<char_t, T, node_t extends ITrieNode<char_t, T, node_t>> {

    node_t createNode(node_t parent, char_t key);

    node_t getRoot();

    /**
     * @return <code>null</code> if undefined.
     */
    node_t find(Vector<char_t> vec);

    /**
     * @return non-<code>null</code> node.
     */
    node_t findOrCreate(Vector<char_t> vec);

    default boolean isDefined(Vector<char_t> vec) {
        node_t node = find(vec);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    default node_t remove(Vector<char_t> vec) {
        node_t node = find(vec);
        if (node == null)
            return null;
        node.detach();
        return node;
    }

    void clear();

    node_t findShortestDefinedPrefix(Vector<char_t> vec);

    node_t findLongestDefinedPrefix(Vector<char_t> vec);

    default Vector<node_t> findAll(Vector<char_t> content) {
        return findAll(content, FindStrategy.QUICK_FIRST);
    }

    Vector<node_t> findAll(Vector<char_t> content, FindStrategy strategy);

}
