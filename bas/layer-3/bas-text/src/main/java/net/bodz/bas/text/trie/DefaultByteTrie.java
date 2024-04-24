package net.bodz.bas.text.trie;

public class DefaultByteTrie<T>
        extends ByteTrie<T, DefaultNode<Byte, T>> {

    public DefaultByteTrie() {
        super((ITrie<Byte, T, DefaultNode<Byte, T>> trie, DefaultNode<Byte, T> parent,
                Byte key) -> new DefaultNode<>(trie, parent, key));
    }

}
