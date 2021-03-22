package net.bodz.bas.text.trie;

public abstract class AbstractTrie<C, T, node_t extends ITrieNode<C, T>>
        implements
            ITrie<C, T> {

    protected final node_t root = createNode(null, null);

    protected abstract node_t createNode(node_t parent, C key);

    @Override
    public node_t getRoot() {
        return root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public node_t resolve(Iterable<C> kSeq) {
        node_t node = root;
        for (C k : kSeq)
            node = (node_t) node.getOrAddChild(k);
        return node;
    }

    @SuppressWarnings("unchecked")
    @Override
    public node_t findOverlap(Iterable<C> kSeq) {
        node_t node = root;
        for (C k : kSeq) {
            node = (node_t) node.getChild(k);
            if (node == null)
                return null;
            if (node.isDefined())
                return node;
        }
        return node;
    }

}
