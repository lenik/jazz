package net.bodz.bas.l10n.zh.acl;

import java.util.Arrays;

import org.junit.Test;

public class SubstTrieTest {

    @Test
    public void testOverlappedWord() {
        SubstTrie trie = new SubstTrie();
        trie.findOrCreate("abc").setData(Arrays.asList("A"));
        trie.findOrCreate("cart").setData(Arrays.asList("C"));
        String out = trie.apply("abcart");
        System.out.println(out);
    }

}
