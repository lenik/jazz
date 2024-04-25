package net.bodz.lily.entity.ws;

import java.util.Set;

import net.bodz.bas.text.trie.CharTrie;
import net.bodz.bas.text.trie.ITrie;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class ECTrie
        extends CharTrie<Set<IEntityCommandType>, ECNode> {

    public ECTrie() {
        super((ITrie<Character, Set<IEntityCommandType>, ECNode> trie, ECNode parent, Character key) -> new ECNode(trie,
                parent, key));
    }

}
