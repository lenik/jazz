package net.bodz.bas.text.trie;

import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;

public class ByteTrie<T, node_t extends ITrieNode<Byte, T, node_t>>
        extends AbstractTrie<Byte, T, node_t> {

    protected ByteTrie() {
        super();
    }

    public ByteTrie(INodeFactory<Byte, T, node_t> nodeFactory) {
        super(nodeFactory);
    }

    public node_t find(byte[] bytes) {
        return find(bytes, 0, bytes.length);
    }

    public node_t find(byte[] bytes, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            byte b = bytes[i];
            node = node.getChild(b);
            if (node == null)
                return null;
        }
        return node;
    }

    public node_t findOrCreate(byte[] bytes) {
        return findOrCreate(bytes, 0, bytes.length);
    }

    public node_t findOrCreate(byte[] bytes, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            byte b = bytes[i];
            node = node.getOrAddChild(b);
        }
        return node;
    }

    public final Vector<node_t> findAll(byte[] content) {
        return findAll(content, 0, content.length);
    }

    public final Vector<node_t> findAll(byte[] content, int start, int end) {
        return findAll(Vectors.ofByteArray(content, start, end));
    }

}
