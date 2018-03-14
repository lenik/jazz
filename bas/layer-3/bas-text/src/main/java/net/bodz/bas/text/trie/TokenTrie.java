package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenTrie<T>
        implements ITrie<String, T> {

    private final Node<T> root = new Node<T>();

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public Node<T> resolve(Iterable<String> tokens) {
        Node<T> node = root;
        for (String token : tokens) {
            node = node.getOrAddChild(token);
        }
        return node;
    }

    /**
     * @return heads
     */
    @Override
    public int[] scanTries(List<String> tokens) {
        int len = tokens.size();
        int heads[] = new int[len];
        Arrays.fill(heads, -1);
        for (int start = 0; start < len; start++)
            // if (start == 0 || root[start] >= 0)
            findMaxTrie(tokens, start, len, heads);
        return heads;
    }

    void findMaxTrie(List<String> tokens, int start, int end, int heads[]) {
        Node<?> node = this.root;
        for (int i = start; i < end; i++) {
            String token = tokens.get(i);
            node = node.getChild(token);
            if (node == null)
                return;
            if (node.isDefined())
                if (heads[i] == -1)
                    heads[i] = start;
        }
    }

    public static class Node<T>
            implements ITrie.Node<String, T> {

        private Map<String, Node<T>> childMap;
        private T data;

        public Node() {
            childMap = new HashMap<>();
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
        public boolean isChild(String childKey) {
            return childMap.containsKey(childKey);
        }

        @Override
        public Node<T> getChild(String childKey) {
            return childMap.get(childKey);
        }

        @Override
        public Node<T> getOrAddChild(String childKey) {
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

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return childMap.keySet().toString();
        }

    }

}
