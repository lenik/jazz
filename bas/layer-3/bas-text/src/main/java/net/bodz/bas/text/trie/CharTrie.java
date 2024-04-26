package net.bodz.bas.text.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.t.ref.MutableInt;
import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;

public class CharTrie<T, node_t extends ITrieNode<Character, T, node_t>>
        extends AbstractTrie<Character, T, node_t>
        implements
            Map<String, T> {

    protected CharTrie() {
        super();
    }

    public CharTrie(INodeFactory<Character, T, node_t> nodeFactory) {
        super(nodeFactory);
    }

    public node_t find(char[] chars) {
        return find(chars, 0, chars.length);
    }

    public node_t find(char[] chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars[i];
            node = node.getChild(ch);
            if (node == null)
                return null;
        }
        return node;
    }

    public node_t findOrCreate(char[] chars) {
        return findOrCreate(chars, 0, chars.length);
    }

    public node_t findOrCreate(char[] chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars[i];
            node = node.getOrAddChild(ch);
        }
        return node;
    }

    public node_t find(CharSequence chars) {
        return find(chars, 0, chars.length());
    }

    public node_t find(CharSequence chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            node = node.getChild(ch);
            if (node == null)
                return null;
        }
        return node;
    }

    public node_t findOrCreate(CharSequence chars) {
        return findOrCreate(chars, 0, chars.length());
    }

    public node_t findOrCreate(CharSequence chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            node = node.getOrAddChild(ch);
        }
        return node;
    }

    public node_t findShortestDefinedPrefix(CharSequence string) {
        return findShortestDefinedPrefix(Vectors.ofString(string));
    }

    public node_t findLongestDefinedPrefix(CharSequence string) {
        return findLongestDefinedPrefix(Vectors.ofString(string));
    }

    public boolean isDefined(char[] chars) {
        return isDefined(chars, 0, chars.length);
    }

    public boolean isDefined(char[] chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    public boolean isDefined(CharSequence chars) {
        return isDefined(chars, 0, chars.length());
    }

    public boolean isDefined(CharSequence chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    public node_t remove(char[] chars) {
        return remove(chars, 0, chars.length);
    }

    public node_t remove(char[] chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return null;
        node.detach();
        return node;
    }

    public node_t remove(CharSequence chars) {
        return remove(chars, 0, chars.length());
    }

    public node_t remove(CharSequence chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return null;
        node.detach();
        return node;
    }

    public Vector<node_t> findAll(CharSequence content) {
        return findAll(Vectors.ofString(content));
    }

    /** ⇱ Implementation Of {@link Map}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        MutableInt size = new MutableInt();
        root.accept_leaf((node_t node) -> {
            size.value++;
            return true;
        });
        return size.value;
    }

    @Override
    public boolean isEmpty() {
        return ! root.accept_leaf((node_t node) -> false);
    }

    @Override
    public final boolean containsKey(Object key) {
        if (key instanceof String)
            return containsKey((String) key);
        return false;
    }

    public boolean containsKey(String key) {
        if (key == null)
            return false;
        node_t node = find(key);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    @Override
    public boolean containsValue(Object value) {
        return ! root.accept_leaf((node_t node) -> ! Nullables.equals(node.getData(), value));
    }

    @Override
    public T get(Object key) {
        if (! (key instanceof String))
            return null;
        String strKey = (String) key;
        return get(strKey);
    }

    public T get(String key) {
        if (key == null)
            throw new NullPointerException("key");
        node_t node = find(key);
        if (node == null)
            return null;
        else
            return node.getData();
    }

    @Override
    public T put(String key, T value) {
        if (key == null)
            throw new NullPointerException("key");
        node_t node = findOrCreate(key);
        T oldData = node.getData();
        node.setData(value);
        return oldData;
    }

    @Override
    public T remove(Object key) {
        if (! (key instanceof String))
            return null;
        String strKey = (String) key;
        return remove(strKey);
    }

    public T remove(String key) {
        if (key == null)
            throw new NullPointerException("key");
        node_t node = find(key);
        if (node == null)
            return null;
        T data = node.getData();
        node.detach();
        return data;
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        for (String key : m.keySet()) {
            T value = m.get(key);
            put(key, value);
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    public String getPath(node_t node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.getKey());
            node = node.getParent();
        }
        sb.reverse();
        String path = sb.toString();
        return path;
    }

    @Override
    public Set<String> keySet() {
        final Set<String> keys = new LinkedHashSet<>();
        root.accept_leaf((node_t node) -> keys.add(getPath(node)) || true);
        return keys;

    }

    @Override
    public Collection<T> values() {
        List<T> values = new ArrayList<>();
        root.accept_leaf((node_t node) -> values.add(node.getData()));
        return values;
    }

    @Override
    public Set<Entry<String, T>> entrySet() {
        Set<Entry<String, T>> entries = new LinkedHashSet<>();
        root.accept_leaf((node_t node) -> entries.add(Pair.of(getPath(node), node.getData())) || true);
        return entries;
    }

}
