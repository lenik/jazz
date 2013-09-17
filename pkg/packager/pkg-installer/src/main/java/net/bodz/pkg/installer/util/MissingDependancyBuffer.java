package net.bodz.pkg.installer.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.t.tree.legacy.ITreeCallback;
import net.bodz.pkg.installer.IComponent;

public class MissingDependancyBuffer
        implements ITreeCallback<IComponent> {

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
