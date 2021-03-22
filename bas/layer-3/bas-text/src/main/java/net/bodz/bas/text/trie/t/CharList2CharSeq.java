package net.bodz.bas.text.trie.t;

import java.util.List;

public class CharList2CharSeq
        implements
            CharSequence {

    private final List<Character> list;

    public CharList2CharSeq(List<Character> list) {
        this.list = list;
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public char charAt(int index) {
        return list.get(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        List<Character> subList = list.subList(start, end);
        return new CharList2CharSeq(subList);
    }

}
