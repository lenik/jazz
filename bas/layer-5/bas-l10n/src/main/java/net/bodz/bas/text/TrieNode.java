package net.bodz.bas.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrieNode<T> {

    private Map<Integer, TrieNode<T>> childMap;
    private T data;

    public TrieNode() {
        childMap = new HashMap<>();
    }

    public boolean isDefined() {
        return data != null;
    }

    public void define(T data) {
        this.data = data;
    }

    public boolean isChild(int childKey) {
        return childMap.containsKey(childKey);
    }

    public TrieNode<T> getChild(int childKey) {
        return childMap.get(childKey);
    }

    public TrieNode<T> getOrAddChild(int childKey) {
        TrieNode<T> child = childMap.get(childKey);
        if (child == null) {
            child = new TrieNode<T>();
            childMap.put(childKey, child);
        }
        return child;
    }

    public T getData() {
        return data;
    }

    /** ⇱ Part: Char sequence based keys */
    /* _____________________________ */static section.part __CHAR_SEQ__;

    public TrieNode<T> resolve(CharSequence charSeq) {
        TrieNode<T> node = this;
        int len = charSeq.length();
        for (int i = 0; i < len; i++)
            node = node.getOrAddChild(charSeq.charAt(i));
        return node;
    }

    public static TrieNode<String> buildDictionaryTrie(Set<String> dictionary) {
        TrieNode<String> root = new TrieNode<>();
        for (String word : dictionary)
            root.resolve(word).define(word);
        return root;
    }

    public static <T> TrieNode<T> buildDictionaryTrie(Map<String, T> dictionaryMap) {
        TrieNode<T> root = new TrieNode<>();
        for (Map.Entry<String, T> entry : dictionaryMap.entrySet())
            root.resolve(entry.getKey()).define(entry.getValue());
        return root;
    }

}
