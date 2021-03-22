package net.bodz.bas.text.trie;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrieNode<C, T, node_t extends ITrieNode<C, T>>
        implements
            ITrieNode<C, T> {

    private AbstractTrie<C, T, node_t> trie;
    private node_t parent;
    private Map<C, node_t> childMap;

    private C key;
    private T data;

    public AbstractTrieNode(AbstractTrie<C, T, node_t> trie, node_t parent, C key) {
        this.trie = trie;
        this.parent = parent;
        this.childMap = new HashMap<>();
        this.key = key;
    }

    @Override
    public ITrieNode<C, T> getParent() {
        return parent;
    }

    @Override
    public int getDepth() {
        int depth = 1;
        ITrieNode<?, ?> node = parent;
        while (node != null) {
            depth++;
            node = node.getParent();
        }
        return depth;
    }

    @Override
    public boolean isChild(C childKey) {
        return childMap.containsKey(childKey);
    }

    @Override
    public node_t getChild(C childKey) {
        return childMap.get(childKey);
    }

    @SuppressWarnings("unchecked")
    @Override
    public node_t getOrAddChild(C childKey) {
        node_t child = childMap.get(childKey);
        if (child == null) {
            child = trie.createNode((node_t) this, childKey);
            childMap.put(childKey, child);
        }
        return child;
    }

    @Override
    public C getKey() {
        return key;
    }

    @Override
    public boolean isDefined() {
        return data != null;
    }

    @Override
    public void define(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return key + ":" + data;
    }

}
