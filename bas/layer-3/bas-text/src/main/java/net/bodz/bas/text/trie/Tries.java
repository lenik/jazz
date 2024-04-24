package net.bodz.bas.text.trie;

import java.util.Map;
import java.util.Set;

public class Tries {

    public static DefaultCharTrie<String> buildLookupTrie(Set<String> words) {
        DefaultCharTrie<String> trie = new DefaultCharTrie<String>();
        for (String word : words)
            trie.findOrCreate(word).setData(word);
        return trie;
    }

    public static <T> DefaultCharTrie<T> buildSubstTrie(Map<String, T> substMap) {
        DefaultCharTrie<T> trie = new DefaultCharTrie<T>();
        for (Map.Entry<String, T> entry : substMap.entrySet())
            trie.findOrCreate(entry.getKey()).setData(entry.getValue());
        return trie;
    }

}
