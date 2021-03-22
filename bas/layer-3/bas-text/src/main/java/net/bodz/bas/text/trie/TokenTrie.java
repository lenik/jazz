package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.List;

public class TokenTrie<T>
        extends AbstractTrie<String, T, TokenTrie.Node<T>> {

    @Override
    protected Node<T> createNode(Node<T> parent, String key) {
        return new Node<T>(this, parent, key);
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
            extends AbstractTrieNode<String, T, Node<T>> {

        public Node(TokenTrie<T> trie, Node<T> parent, String key) {
            super(trie, parent, key);
        }

    }

}
