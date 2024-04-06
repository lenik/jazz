package net.bodz.lily.entity.ws;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.t.preorder.PrefixMap;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class CommandLocator {

    Map<String, IEntityCommandType> idMap = new HashMap<>();
    Map<String, IEntityCommandType> nameMap = new HashMap<>();
    PrefixMap<IEntityCommandType> namePrefixMap = new PrefixMap<>();
    Map<String, IEntityCommandType> contentNameMap = new HashMap<>();
    PrefixMap<IEntityCommandType> contentNamePrefixMap = new PrefixMap<>();
    Set<String> otherIds = new HashSet<>();
    Set<IEntityCommandType> others = new TreeSet<>(PriorityComparator.INSTANCE);

    public boolean contains(IEntityCommandType command) {
        String id = command.getUniqueId();
        if (id == null)
            throw new NullPointerException("id");
        return idMap.containsKey(id);
    }

    public boolean checkOverlap(IEntityCommandType command) {
        if (contains(command))
            return true;

        String[] names = command.getCommandNames();
        for (String name : names)
            if (name.endsWith("*")) {
                String prefix = name.substring(0, name.length() - 1);
                if (command.isContentCommand()) {
                    if (contentNamePrefixMap.containsKey(prefix))
                        return true;
                } else {
                    if (namePrefixMap.containsKey(prefix))
                        return true;
                }
            } else {
                if (command.isContentCommand()) {
                    if (contentNameMap.containsKey(name))
                        return true;
                } else {
                    if (nameMap.containsKey(name))
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
                if (name.endsWith("*")) {
                    String prefix = name.substring(0, name.length() - 1);
                    if (command.isContentCommand()) {
                        contentNamePrefixMap.put(prefix, command);
                    } else {
                        namePrefixMap.put(prefix, command);
                    }
                } else {
                    if (command.isContentCommand()) {
                        contentNameMap.put(name, command);
                    } else {
                        nameMap.put(name, command);
                    }
                }
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
                if (name.endsWith("*")) {
                    String prefix = name.substring(0, name.length() - 1);
                    if (command.isContentCommand()) {
                        contentNamePrefixMap.remove(prefix);
                    } else {
                        namePrefixMap.remove(prefix);
                    }
                } else {
                    if (command.isContentCommand()) {
                        contentNameMap.remove(name);
                    } else {
                        nameMap.remove(name);
                    }
                }
            }
        } else {
            otherIds.remove(id);
            others.remove(command);
        }
    }

    public void clear() {
        idMap.clear();
        nameMap.clear();
        namePrefixMap.clear();
        contentNameMap.clear();
        contentNamePrefixMap.clear();
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

        cmd = nameMap.get(name);
        if (cmd != null)
            return cmd;

        cmd = namePrefixMap.meet(name);
        if (cmd != null)
            return cmd;

        return null;
    }

    public IEntityCommandType findContentName(String name) {
        IEntityCommandType cmd = idMap.get(name);
        if (cmd != null)
            return cmd;

        cmd = contentNameMap.get(name);
        if (cmd != null)
            return cmd;

        cmd = contentNamePrefixMap.meet(name);
        if (cmd != null)
            return cmd;

        return null;
    }

    public Set<IEntityCommandType> getOthers() {
        return others;
    }

}
