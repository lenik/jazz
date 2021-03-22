package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.text.trie.t.CharList2CharSeq;
import net.bodz.bas.text.trie.t.CharSeqIterable;

public class CharTrie<T>
        extends AbstractTrie<Character, T, CharTrie.Node<T>> {

    @Override
    protected Node<T> createNode(Node<T> parent, Character key) {
        return new Node<T>(this, parent, key);
    }

    public Node<T> resolve(CharSequence charSeq) {
        return resolve(new CharSeqIterable(charSeq));
    }

    public Node<T> findOverlap(CharSequence charSeq) {
        return findOverlap(new CharSeqIterable(charSeq));
    }

    @Override
    public final int[] scanTries(List<Character> content) {
        return scanTries(new CharList2CharSeq(content));
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
        Node<T> node = this.root;
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
            extends AbstractTrieNode<Character, T, Node<T>> {

        public Node(CharTrie<T> trie, Node<T> parent, Character key) {
            super(trie, parent, key);
        }

    }

}
