package net.bodz.redist.installer.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.redist.installer.IComponent;
import net.bodz.bas.collection.tree.TreeCallback;

public class MissingDependancyBuffer
        implements TreeCallback<IComponent> {

    public static class Entry {

        public final int level;
        public final IComponent component;

        public Entry(int level, IComponent c) {
            this.level = level;
            this.component = c;
        }

    }

    private List<Entry> list;

    public MissingDependancyBuffer() {
        list = new ArrayList<Entry>();
    }

    @Override
    public int each(IComponent node, int level) {
        // add mid-parents: most-recent, ..., node.parent, node
        // most recent entry with recent.level < level

        Entry missingEntry = new Entry(level, node);
        // for (int i = list.size() - 1; i >= 0; i--) {
        // Entry prev = list.get(i);
        // if (prev.level < level) {
        // }
        // }
        list.add(missingEntry);
        return OK;
    }

    public List<Entry> getList() {
        return list;
    }

}
