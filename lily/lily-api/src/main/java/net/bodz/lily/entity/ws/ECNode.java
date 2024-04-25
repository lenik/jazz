package net.bodz.lily.entity.ws;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.text.trie.ITrie;
import net.bodz.bas.text.trie.MutableTrieNode;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class ECNode
        extends MutableTrieNode<Character, Set<IEntityCommandType>, ECNode> {

    public ECNode(ITrie<Character, Set<IEntityCommandType>, ECNode> trie, ECNode parent, Character key) {
        super(trie, parent, key);
    }

    @Override
    public boolean isDefined() {
        return data != null && ! data.isEmpty();
    }

    Set<IEntityCommandType> make() {
        if (data == null)
            data = new TreeSet<>(PriorityComparator.INSTANCE);
        return data;
    }

    public Set<IEntityCommandType> getCommands() {
        if (data == null)
            return Collections.emptySet();
        else
            return data;
    }

    public void addCommand(IEntityCommandType command) {
        make().add(command);
    }

    public boolean removeCommand(IEntityCommandType command) {
        if (data != null)
            return data.remove(command);
        return false;
    }

}
