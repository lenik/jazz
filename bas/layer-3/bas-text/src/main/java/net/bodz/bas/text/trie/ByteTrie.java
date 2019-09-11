package net.bodz.bas.text.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ByteTrie<T>
        implements ITrie<Byte, T> {

    private final Node<T> root = new Node<T>();

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public Node<T> resolve(Iterable<Byte> byteSeq) {
        Node<T> node = root;
        for (Byte byt : byteSeq)
            node = node.getOrAddChild(byt);
        return node;
    }

    public Node<T> resolve(byte[] bytes) {
        return resolve(bytes, 0, bytes.length);
    }

    public Node<T> resolve(byte[] bytes, int off, int len) {
        int end = off + len;
        Node<T> node = root;
        for (int i = off; i < end; i++)
            node = node.getOrAddChild(bytes[i]);
        return node;
    }

    @Override
    public final int[] scanTries(List<Byte> seq) {
        return scanTries(new ByteListSeq(seq));
    }

    public final int[] scanTries(byte[] content) {
        return scanTries(content, 0, content.length);
    }

    public final int[] scanTries(byte[] content, int off, int len) {
        return scanTries(new ByteArraySeq(content, off, len));
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
        Node<?> node = this.root;
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
            implements ITrie.Node<Byte, T> {
        private Map<Byte, Node<T>> childMap;
        private T data;

        public Node() {
            childMap = new HashMap<Byte, Node<T>>();
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
        public boolean isChild(Byte childKey) {
            return childMap.containsKey(childKey);
        }

        @Override
        public Node<T> getChild(Byte childKey) {
            return childMap.get(childKey);
        }

        @Override
        public Node<T> getOrAddChild(Byte childKey) {
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

interface ByteSequence {

    int length();

    byte byteAt(int index);

    ByteSequence subSequence(int start, int end);

}

class ByteArraySeq
        implements ByteSequence {

    byte[] array;
    int off;
    int len;

    public ByteArraySeq(byte[] array) {
        this(array, 0, array.length);
    }

    public ByteArraySeq(byte[] array, int off, int len) {
        this.array = array;
        this.off = off;
        this.len = len;
    }

    @Override
    public int length() {
        return len;
    }

    @Override
    public byte byteAt(int index) {
        return array[off + index];
    }

    @Override
    public ByteSequence subSequence(int start, int end) {
        return new ByteArraySeq(array, off + start, end - start);
    }

}

class ByteListSeq
        implements ByteSequence {

    private final List<Byte> list;

    public ByteListSeq(List<Byte> list) {
        this.list = list;
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public byte byteAt(int index) {
        return list.get(index);
    }

    @Override
    public ByteSequence subSequence(int start, int end) {
        List<Byte> subList = list.subList(start, end);
        return new ByteListSeq(subList);
    }

}
