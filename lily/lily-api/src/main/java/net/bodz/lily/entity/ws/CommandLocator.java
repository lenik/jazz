package net.bodz.lily.entity.ws;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class CommandLocator {

    Map<String, IEntityCommandType> idMap = new HashMap<>();

    ECTrie trie = new ECTrie();
    ECTrie contentTrie = new ECTrie();

    Set<IEntityCommandType> others = new TreeSet<>(PriorityComparator.INSTANCE);

    public boolean contains(IEntityCommandType command) {
        String id = command.getUniqueId();
        if (id == null)
            throw new NullPointerException("id");
        return idMap.containsKey(id);
    }

    public void checkConflict(IEntityCommandType command) {
        if (contains(command))
            throw new DuplicatedKeyException("id: " + command.getUniqueId());

        String[] names = command.getCommandNames();
        for (String name : names) {
            boolean wildcard = name.endsWith("*");
            if (wildcard)
                name = name.substring(0, name.length() - 1);

            ECNode node;
            if (command.isContentCommand())
                node = contentTrie.find(name);
            else
                node = trie.find(name);

            if (node != null)
                for (IEntityCommandType prev : node.getCommands()) {
                    ECmds.checkConflict(prev, command);
                }
        }
    }

    public boolean add(IEntityCommandType command) {
        String id = command.getUniqueId();
        idMap.put(id, command);

        String[] names = command.getCommandNames();
        if (names.length > 0) {
            for (String name : command.getCommandNames()) {
                boolean wildcard = name.endsWith("*");
                if (wildcard)
                    name = name.substring(0, name.length() - 1);

                ECNode node;
                if (command.isContentCommand())
                    node = contentTrie.findOrCreate(name);
                else
                    node = trie.findOrCreate(name);

                node.setWildcard(wildcard);
                node.addCommand(command);
            }
            return true;
        } else {
            others.add(command);
            return false;
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
            others.remove(command);
        }
    }

    public void clear() {
        idMap.clear();
        trie.clear();
        contentTrie.clear();
        others.clear();
    }

    public IEntityCommandType get(String id) {
        return idMap.get(id);
    }

    public Set<IEntityCommandType> findName(String name) {
        IEntityCommandType cmd = idMap.get(name);
        if (cmd != null)
            return new HashSet<>(Arrays.asList(cmd));

        ECNode node = trie.findLongestDefinedPrefix(name);
        if (node == null)
            return null;
        int matchLen = node.getDepth();
        if (matchLen == name.length() || node.isWildcard())
            return node.getData();
        return null;
    }

    public Set<IEntityCommandType> findContentName(String name) {
        IEntityCommandType cmd = idMap.get(name);
        if (cmd != null)
            return new HashSet<>(Arrays.asList(cmd));

        ECNode node = contentTrie.findLongestDefinedPrefix(name);
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
