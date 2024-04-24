package net.bodz.lily.entity.ws;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.text.trie.DefaultCharTrie;
import net.bodz.bas.text.trie.DefaultNode;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class CommandLocator {

    Map<String, IEntityCommandType> idMap = new HashMap<>();
    DefaultCharTrie<IEntityCommandType> trie = new DefaultCharTrie<>();
    DefaultCharTrie<IEntityCommandType> contentTrie = new DefaultCharTrie<>();

//    Map<RequestKey, IEntityCommandType> keyMap = new HashMap<>();
//    PrefixMap<IEntityCommandType> prefixMap = new PrefixMap<>();

    Set<String> otherIds = new HashSet<>();
    Set<IEntityCommandType> others = new TreeSet<>(PriorityComparator.INSTANCE);

    public boolean contains(IEntityCommandType command) {
        String id = command.getUniqueId();
        if (id == null)
            throw new NullPointerException("id");
        return idMap.containsKey(id);
    }

    public boolean checkConflict(IEntityCommandType command) {
        if (contains(command))
            return true;
        String[] names = command.getCommandNames();
        for (String name : names) {
            boolean wildcard = name.endsWith("*");
            if (wildcard)
                name = name.substring(0, name.length() - 1);
            if (command.isContentCommand()) {
                if (contentTrie.isDefined(name))
                    return true;
            } else {
                if (trie.isDefined(name))
                    return true;
            }
        }
        return false;
    }

    public void add(IEntityCommandType command) {
        String id = command.getUniqueId();
        idMap.put(id, command);

        String[] names = command.getCommandNames();
        if (names.length > 0) {
            for (String name : command.getCommandNames()) {
                boolean wildcard = name.endsWith("*");
                if (wildcard)
                    name = name.substring(0, name.length() - 1);

                DefaultNode<Character, IEntityCommandType> node;
                if (command.isContentCommand())
                    node = contentTrie.findOrCreate(name);
                else
                    node = trie.findOrCreate(name);

                node.setWildcard(wildcard);
                node.setData(command);
            }
        } else {
            otherIds.add(id);
            others.add(command);
        }
    }

    public void remove(IEntityCommandType command) {
        String id = command.getUniqueId();
        idMap.remove(id);

        String[] names = command.getCommandNames();
        if (names.length > 0) {
            for (String name : command.getCommandNames()) {
                boolean wildcard = name.endsWith("*");
                if (wildcard)
                    name = name.substring(0, name.length() - 1);
                if (command.isContentCommand()) {
                    contentTrie.remove(name);
                } else {
                    trie.remove(name);
                }
            }
        } else {
            otherIds.remove(id);
            others.remove(command);
        }
    }

    public void clear() {
        idMap.clear();
        trie.clear();
        contentTrie.clear();
        otherIds.clear();
        others.clear();
    }

    public IEntityCommandType get(String id) {
        return idMap.get(id);
    }

    public IEntityCommandType findName(String name) {
        IEntityCommandType cmd = idMap.get(name);
        if (cmd != null)
            return cmd;

        DefaultNode<Character, IEntityCommandType> node = trie.findLongestDefinedPrefix(name);
        if (node == null)
            return null;
        int matchLen = node.getDepth();
        if (matchLen == name.length() || node.isWildcard())
            return node.getData();
        return null;
    }

    public IEntityCommandType findContentName(String name) {
        IEntityCommandType cmd = idMap.get(name);
        if (cmd != null)
            return cmd;

        DefaultNode<Character, IEntityCommandType> node = contentTrie.findLongestDefinedPrefix(name);
        if (node == null)
            return null;

        int matchLen = node.getDepth();
        if (matchLen == name.length() || node.isWildcard())
            return node.getData();
        return null;
    }

    public Set<IEntityCommandType> getOthers() {
        return others;
    }

}
