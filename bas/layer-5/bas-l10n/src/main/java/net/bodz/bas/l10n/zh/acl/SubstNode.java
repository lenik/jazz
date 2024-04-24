package net.bodz.bas.l10n.zh.acl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.text.trie.ITrie;
import net.bodz.bas.text.trie.MutableTrieNode;

public class SubstNode
        extends MutableTrieNode<Character, List<String>, SubstNode> {

    public SubstNode(ITrie<Character, List<String>, SubstNode> trie, SubstNode parent, Character key) {
        super(trie, parent, key);
    }

    List<String> getList() {
        if (data == null)
            data = new ArrayList<>();
        return data;
    }

    public void addItem(String item) {
        getList().add(item);
    }

}
