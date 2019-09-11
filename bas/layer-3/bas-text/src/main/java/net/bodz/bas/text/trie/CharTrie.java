package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CharTrie<T>
        implements ITrie<Character, T> {

    private final Node<T> root = new Node<T>();

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public Node<T> resolve(Iterable<Character> charSeq) {
        Node<T> node = root;
        for (Character ch : charSeq)
            node = node.getOrAddChild(ch);
        return node;
    }

    public Node<T> resolve(CharSequence charSeq) {
        Node<T> node = root;
        int len = charSeq.length();
        for (int i = 0; i < len; i++)
            node = node.getOrAddChild(charSeq.charAt(i));
        return node;
    }

    @Override
    public final int[] scanTries(List<Character> content) {
        return scanTries(new CharListSeq(content));
    }

    public int[] scanTries(CharSequence content) {
        int len = content.length();
        int heads[] = new int[len];
        Arrays.fill(heads, -1);
        for (int start = 0; start < len; start++)
            // if (start == 0 || root[start] >= 0)
            findMaxTrie(content, start, len, heads);
        return heads;
    }

    void findMaxTrie(CharSequence content, int start, int end, int heads[]) {
        Node<?> node = this.root;
        for (int i = start; i < end; i++) {
            char ch = content.charAt(i);
            node = node.getChild(ch);
            if (node == null)
                return;
            if (node.isDefined())
                if (heads[i] == -1)
                    heads[i] = start;
        }
    }

    public static CharTrie<String> buildDictionaryTrie(Set<String> dictionary) {
        CharTrie<String> trie = new CharTrie<String>();
        for (String word : dictionary)
            trie.resolve(word).define(word);
        return trie;
    }

    public static <T> CharTrie<T> buildDictionaryTrie(Map<String, T> dictionaryMap) {
        CharTrie<T> trie = new CharTrie<T>();
        for (Map.Entry<String, T> entry : dictionaryMap.entrySet())
            trie.resolve(entry.getKey()).define(entry.getValue());
        return trie;
    }

    public static class Node<T>
            implements ITrie.Node<Character, T> {
        private Map<Character, Node<T>> childMap;
        private T data;

        public Node() {
            childMap = new HashMap<Character, Node<T>>();
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
        public boolean isChild(Character childKey) {
            return childMap.containsKey(childKey);
        }

        @Override
        public Node<T> getChild(Character childKey) {
            return childMap.get(childKey);
        }

        @Override
        public Node<T> getOrAddChild(Character childKey) {
            Node<T> child = childMap.get(childKey);
            if (child == null) {
                child = new Node<T>();
                childMap.put(childKey, child);
            }
            return child;
        }

        @Override
        public T getData() {
            return data;
        }

    }

}

class CharListSeq
        implements CharSequence {

    private final List<Character> list;

    public CharListSeq(List<Character> list) {
        this.list = list;
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public char charAt(int index) {
        return list.get(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        List<Character> subList = list.subList(start, end);
        return new CharListSeq(subList);
    }

}
