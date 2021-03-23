package net.bodz.bas.log.impl;

import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.log.util.IListChangeListener;

public class ListEditorBuffer
        implements IListChangeListener {

    LinkedList<ListEditCommand> cmds = new LinkedList<>();

    ListEditCommand last() {
        if (cmds.isEmpty())
            return null;
        return cmds.getLast();
    }

    @Override
    public void onInserted(int start, int len) {
        ListEditCommand last = last();
        if (last != null) {
            int lastEnd = last.start + last.len;
            if (last.start <= start && start <= lastEnd) {
                last.len += len;
                return;
            }
        }
        cmds.add(new ListEditCommand(ListEditType.INSERT, start, len));
    }

    @Override
    public void onRemoved(int start, int len) {
        ListEditCommand last = last();
        if (last != null) {
            int end = start + len;
            if (start <= last.start && last.start <= end) {
                last.start = start;
                last.len += len;
                return;
            }
        }
        cmds.add(new ListEditCommand(ListEditType.REMOVE, start, len));
    }

    @Override
    public void onChanged(int start, int len) {
        ListEditCommand last = last();
        if (last != null) {
            int end = start + len;
            int lastEnd = last.start + last.len;
            if (start < last.start && last.start <= end) {
                last.len += last.start - start;
                last.start = start;
                return;
            }
            if (last.start <= start && start <= lastEnd) {
                if (end > lastEnd)
                    last.len += end - lastEnd;
                return;
            }
        }
        cmds.add(new ListEditCommand(ListEditType.CHANGE, start, len));
    }

    @Override
    public void onCleared() {
        cmds.clear();
        cmds.add(new ListEditCommand());
    }

    public List<ListEditCommand> getList() {
        return cmds;
    }

    public void dump() {
        for (ListEditCommand c : cmds)
            System.out.printf("[%s] %d +%d\n", c.type, c.start, c.len);
    }

}
