package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.text.trie.t.ByteArray2ByteSeq;
import net.bodz.bas.text.trie.t.ByteList2ByteSeq;
import net.bodz.bas.text.trie.t.ByteSequence;

public class ByteTrie<T>
        extends AbstractTrie<Byte, T, ByteTrie.Node<T>> {

    @Override
    protected Node<T> createNode(Node<T> parent, Byte key) {
        return new Node<T>(this, parent, key);
    }

    public Node<T> resolve(byte[] bytes) {
        return resolve(bytes, 0, bytes.length);
    }

    public Node<T> resolve(byte[] bytes, int start, int end) {
        return super.resolve(new ByteArray2ByteSeq(bytes, start, end));

    }

    @Override
    public final int[] scanTries(List<Byte> seq) {
        return scanTries(new ByteList2ByteSeq(seq));
    }

    public final int[] scanTries(byte[] content) {
        return scanTries(content, 0, content.length);
    }

    public final int[] scanTries(byte[] content, int start, int end) {
        return scanTries(new ByteArray2ByteSeq(content, start, end));
    }

    public int[] scanTries(ByteSequence content) {
        int len = content.length();
        int heads[] = new int[len];
        Arrays.fill(heads, -1);
        for (int start = 0; start < len; start++)
            // if (start == 0 || root[start] >= 0)
            findMaxTrie(content, start, len, heads);
        return heads;
    }

    void findMaxTrie(ByteSequence content, int start, int end, int heads[]) {
        Node<T> node = this.root;
        for (int i = start; i < end; i++) {
            byte byt = content.byteAt(i);
            node = node.getChild(byt);
            if (node == null)
                return;
            if (node.isDefined())
                if (heads[i] == -1)
                    heads[i] = start;
        }
    }

    public static class Node<T>
            extends AbstractTrieNode<Byte, T, Node<T>> {

        public Node(ByteTrie<T> trie, Node<T> parent, Byte key) {
            super(trie, parent, key);
        }

    }

}
