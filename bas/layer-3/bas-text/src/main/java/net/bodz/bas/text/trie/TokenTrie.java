package net.bodz.bas.text.trie;

import java.util.HashMap;
import java.util.Map;

public class TokenTrie<T> {

    private final Node<T> root = new Node<T>();

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> resolve(Iterable<String> tokens) {
        Node<T> node = root;
        for (String token : tokens) {
            node = node.getOrAddChild(token);
        }
        return node;
    }

    public static class Node<T> {

        private Map<String, Node<T>> childMap;
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

        public boolean isChild(String childKey) {
            return childMap.containsKey(childKey);
        }

        public Node<T> getChild(String childKey) {
            return childMap.get(childKey);
        }

        public Node<T> getOrAddChild(String childKey) {
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

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return childMap.keySet().toString();
        }

    }

}
