package net.bodz.bas.text.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharTrie<T> {

    private final Node<T> root = new Node<T>();

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> resolve(CharSequence charSeq) {
        Node<T> node = root;
        int len = charSeq.length();
        for (int i = 0; i < len; i++)
            node = node.getOrAddChild(charSeq.charAt(i));
        return node;
    }

    public static CharTrie<String> buildDictionaryTrie(Set<String> dictionary) {
        CharTrie<String> trie = new CharTrie<>();
        for (String word : dictionary)
            trie.resolve(word).define(word);
        return trie;
    }

    public static <T> CharTrie<T> buildDictionaryTrie(Map<String, T> dictionaryMap) {
        CharTrie<T> trie = new CharTrie<>();
        for (Map.Entry<String, T> entry : dictionaryMap.entrySet())
            trie.resolve(entry.getKey()).define(entry.getValue());
        return trie;
    }

    public static class Node<T> {
        private Map<Integer, Node<T>> childMap;
        private T data;

        public Node() {
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

        public Node<T> getChild(int childKey) {
            return childMap.get(childKey);
        }

        public Node<T> getOrAddChild(int childKey) {
            Node<T> child = childMap.get(childKey);
            if (child == null) {
                child = new Node<T>();
                childMap.put(childKey, child);
            }
            return child;
        }

        public T getData() {
            return data;
        }

    }

}
