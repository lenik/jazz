package net.bodz.bas.text.trie;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;

public abstract class AbstractTrie<char_t, T, node_t extends ITrieNode<char_t, T, node_t>>
        implements
            ITrie<char_t, T, node_t> {

    final INodeFactory<char_t, T, node_t> nodeFactory;
    protected final node_t root;

    @SuppressWarnings("unchecked")
    protected AbstractTrie() {
        if (this instanceof INodeFactory)
            this.nodeFactory = (INodeFactory<char_t, T, node_t>) this;
        else
            throw new IllegalUsageException();
        this.root = createNode(null, null);
    }

    public AbstractTrie(INodeFactory<char_t, T, node_t> nodeFactory) {
        if (nodeFactory == null)
            throw new NullPointerException("nodeFactory");
        this.nodeFactory = nodeFactory;
        this.root = createNode(null, null);
    }

    @Override
    public node_t createNode(node_t parent, char_t key) {
        node_t node = nodeFactory.create(this, parent, key);
        return node;
    }

    @Override
    public node_t getRoot() {
        return root;
    }

    @Override
    public node_t find(Vector<char_t> vec) {
        node_t node = root;
        for (char_t k : vec) {
            node = node.getChild(k);
            if (node == null)
                return null;
        }
        return node;
    }

    @Override
    public node_t findOrCreate(Vector<char_t> vec) {
        node_t node = root;
        for (char_t k : vec)
            node = node.getOrAddChild(k);
        return node;
    }

    @Override
    public void clear() {
        root.clear();
    }

    @Override
    public node_t findShortestDefinedPrefix(Vector<char_t> vec) {
        node_t node = root;
        for (char_t k : vec) {
            node = node.getChild(k);
            if (node == null)
                return null;
            if (node.isDefined())
                return node;
        }
        return node;
    }

    @Override
    public node_t findLongestDefinedPrefix(Vector<char_t> vec) {
        node_t node = root;
        node_t lastDefined = null;
        for (char_t k : vec) {
            node = node.getChild(k);
            if (node == null)
                return lastDefined;
            if (node.isDefined())
                lastDefined = node;
        }
        return lastDefined;
    }

    @Override
    public Vector<node_t> findAll(Vector<char_t> content, FindStrategy strategy) {
        if (strategy != FindStrategy.QUICK_FIRST)
            throw new IllegalArgumentException();

        int len = content.length();
        Vector<node_t> matchv = Vectors.ofSize(len);

        int start = 0;
        while (start < len) {
            // if (start == 0 || root[start] >= 0)
            node_t node = this.root;
            node_t longest = null;
            int longestPos = -1;
            for (int i = start; i < len; i++) {
                char_t ch = content.get(i);
                node = node.getChild(ch);
                if (node == null)
                    break;
                if (node.isDefined()) {
                    longest = node;
                    longestPos = i;
                }
            }
            if (longest != null) {
                matchv.set(longestPos, longest);
                start = longestPos + 1;
                continue;
            }
            start++;
        }
        return matchv;
    }

}
