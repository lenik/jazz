package net.bodz.bas.text.trie;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;

public class CharTrieTest
        extends Assert {

    @Test
    public void testScanTrie() {
        DefaultCharTrie<String> trie = new DefaultCharTrie<String>();
        trie.findOrCreate("abc").setData("1");
        trie.findOrCreate("cart").setData("1");

        Vector<DefaultNode<Character, String>> matchv = trie.findAll(Vectors.ofString("abcart"));
        System.out.println(matchv);
    }

}
