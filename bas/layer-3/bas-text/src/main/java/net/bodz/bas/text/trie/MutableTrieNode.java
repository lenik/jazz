package net.bodz.bas.text.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MutableTrieNode<char_t, T, self_t extends MutableTrieNode<char_t, T, self_t>>
        extends TrieNode<char_t, T, self_t> {

    protected T data;
    private Map<char_t, self_t> childMap;

    public MutableTrieNode(ITrie<char_t, T, self_t> trie, self_t parent, char_t key) {
        super(trie, parent, key);
        this.childMap = new HashMap<>();
    }

    @Override
    public Set<char_t> getChildKeys() {
        return childMap.keySet();
    }

    @Override
    public boolean isChild(char_t childKey) {
        return childMap.containsKey(childKey);
    }

    @Override
    public self_t getChild(char_t childKey) {
        return childMap.get(childKey);
    }

    @SuppressWarnings("unchecked")
    @Override
    public self_t getOrAddChild(char_t childKey) {
        self_t child = childMap.get(childKey);
        if (child == null) {
            child = trie.createNode((self_t) this, childKey);
            childMap.put(childKey, child);
        }
        return child;
    }

    @Override
    public self_t removeChild(char_t childKey) {
        self_t child = childMap.remove(childKey);
        if (! isDefined() && childMap.isEmpty() && parent != null)
            parent.removeChild(key);
        return child;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public T setData(T data) {
        T oldData = this.data;
        this.data = data;
        return oldData;
    }

    @Override
    public void addData(T data) {
        if (data == null)
            throw new NullPointerException("data");
        if (this.data != null)
            throw new IllegalStateException("already have data");
        this.data = data;
    }

    @Override
    public boolean removeData() {
        T data = this.data;
        this.data = null;
        return data != null;
    }

    @Override
    public boolean isDefined() {
        return data != null;
    }

    @Override
    public boolean isEmpty() {
        return ! isDefined() && childMap.isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return isDefined() || ! childMap.isEmpty();
    }

    @Override
    public self_t detach(boolean detachEmptyAncestors) {
        if (parent != null) {
            parent.removeChild(key);
            if (detachEmptyAncestors && parent.isEmpty())
                return parent.detach(true);
        }
        @SuppressWarnings("unchecked")
        self_t thisNode = (self_t) this;
        return thisNode;
    }

    @Override
    public void clear() {
        data = null;
        childMap.clear();
    }

}
