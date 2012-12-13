package net.bodz.swt.c3.pageflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.t.pojo.PathEntries;

public class History {

    private LinkedList<PathEntries> list;
    private int capacity;
    private int currentIndex;

    private List<HistoryChangeListener> historyChangeListeners;
    private List<HistoryRemovedListener> historyRemovedListeners;

    public History(int capacity) {
        if (capacity < 1)
            throw new OutOfDomainException("max", capacity, 1);
        this.capacity = capacity;
        // int initialCapacity = Math.min(100, max);
        list = new LinkedList<PathEntries>();
        currentIndex = -1;
    }

    public int size() {
        return list.size();
    }

    public int getBackwards() {
        return currentIndex;
    }

    public int getForwards() {
        return list.size() - currentIndex - 1;
    }

    /**
     * @return <code>null</code> if no current path.
     */
    public PathEntries get() {
        return get(0);
    }

    /**
     * @return <code>null</code> if no current path.
     */
    public PathEntries get(int delta) {
        int i = currentIndex + delta;
        if (i < 0 || i >= list.size())
            return null;
        return list.get(i);
    }

    public boolean has(int relative) {
        int i = currentIndex + relative;
        return i >= 0 && i < list.size();
    }

    /**
     * @return previous path.
     */
    PathEntries go(PathEntries newpath) {
        int changes = 0;
        PathEntries prev = get();
        if (newpath == null)
            throw new NullPointerException("currentPath");

        if (currentIndex == list.size() - 1) {
            list.add(newpath);
            currentIndex = list.size() - 1;
        } else if (0 <= currentIndex && currentIndex < list.size() - 1) {
            list.set(++currentIndex, newpath);
        } else {
            throw new IllegalStateException(String.format("bad current index/size: %d/%d", currentIndex, list.size()));
        }

        while (list.size() > currentIndex + 1) { // drop forwards
            PathEntries removed = list.removeLast();
            fireHistoryRemoved(removed);
            changes |= HistoryChangeEvent.DROP_FORWARDS;
        }

        while (list.size() > capacity) { // drop backwards
            PathEntries removed = list.removeFirst();
            fireHistoryRemoved(removed);
            currentIndex--;
            changes |= HistoryChangeEvent.DROP_BACKWARDS;
        }

        assert currentIndex == list.size() - 1;
        fireHistoryChange(changes);
        return prev;
    }

    /**
     * @return <code>true</code> if current index changed.
     */
    boolean jump(int relative) {
        if (relative == 0)
            return false;
        int i = currentIndex + relative;
        if (i < 0)
            i = 0;
        if (i >= list.size())
            i = list.size() - 1;
        if (currentIndex != i) {
            currentIndex = i;
            fireHistoryChange(HistoryChangeEvent.JUMP);
            return true;
        }
        return false;
    }

    public List<PathEntries> list(int offset, int count) {
        int start = Math.max(0, currentIndex + offset);
        int end = Math.min(list.size(), start + count);
        int num = end - start;
        List<PathEntries> copy = new ArrayList<PathEntries>(num);
        ListIterator<PathEntries> iter = list.listIterator(start);
        while (num > 0) {
            PathEntries path = iter.next();
            copy.add(path);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(list.size() * 32);
        int relative = -currentIndex;
        for (PathEntries path : list) {
            buf.append(relative + ". " + path + "\n");
            relative++;
        }
        return buf.toString();
    }

    public void addHistoryChangeListener(HistoryChangeListener l) {
        if (historyChangeListeners == null)
            historyChangeListeners = new ArrayList<HistoryChangeListener>(1);
        historyChangeListeners.add(l);
    }

    public void removeHistoryChangeListener(HistoryChangeListener l) {
        if (historyChangeListeners != null)
            historyChangeListeners.remove(l);
    }

    protected void fireHistoryChange(int changeType) {
        if (historyChangeListeners != null) {
            HistoryChangeEvent e = new HistoryChangeEvent(this, changeType);
            for (HistoryChangeListener l : historyChangeListeners)
                l.historyChange(e);
        }
    }

    public void addHistoryRemovedListener(HistoryRemovedListener l) {
        if (historyRemovedListeners == null)
            historyRemovedListeners = new ArrayList<HistoryRemovedListener>(1);
        historyRemovedListeners.add(l);
    }

    public void removeHistoryRemovedListener(HistoryRemovedListener l) {
        if (historyRemovedListeners != null)
            historyRemovedListeners.remove(l);
    }

    protected void fireHistoryRemoved(PathEntries path) {
        if (historyRemovedListeners != null) {
            int refCount = Collections.frequency(list, path);
            HistoryRemovedEvent e = new HistoryRemovedEvent(this, path, refCount);
            for (HistoryRemovedListener l : historyRemovedListeners)
                l.historyRemoved(e);
        }
    }

}
