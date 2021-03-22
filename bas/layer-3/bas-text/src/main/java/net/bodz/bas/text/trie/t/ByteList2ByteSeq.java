package net.bodz.bas.text.trie.t;

import java.util.Iterator;
import java.util.List;

public class ByteList2ByteSeq
        implements
            ByteSequence,
            Iterable<Byte> {

    private final List<Byte> list;

    public ByteList2ByteSeq(List<Byte> list) {
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
        return new ByteList2ByteSeq(subList);
    }

    @Override
    public Iterator<Byte> iterator() {
        return list.iterator();
    }

}
