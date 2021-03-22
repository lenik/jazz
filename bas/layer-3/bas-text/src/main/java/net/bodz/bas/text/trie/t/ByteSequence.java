package net.bodz.bas.text.trie.t;

public interface ByteSequence {

    int length();

    byte byteAt(int index);

    ByteSequence subSequence(int start, int end);

}
