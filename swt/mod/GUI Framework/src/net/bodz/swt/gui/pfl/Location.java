package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private List<String> history;
    private int          index;

    public Location() {
        history = new ArrayList<String>();
        index = -1;
    }

    public String get() {
        if (index == -1)
            return null;
        return history.get(index);
    }

    public String set(String next) {
        String prev = get();
        String resolved = resolv(prev, next);
        history.add(++index, resolved);
        // clear the broken forwards: (index, len)
        int keeplen = index + 1;
        int len = history.size();
        while (len > keeplen)
            history.remove(--len);
        locationChange(prev, resolved);
        return resolved;
    }

    public final String resolv(String next) {
        String prev = get();
        return resolv(prev, next);
    }

    public final String resolv(String prev, String next) {
        // XXX - prev = normalize(prev);
        String resolved = _resolv(prev, next);
        String normalized = normalize(resolved);
        return normalized;
    }

    protected String _resolv(String prev, String next) {
        if (prev == null)
            return next;
        if (next == null)
            return null;
        if (next.startsWith("/"))
            return next;
        if (!prev.endsWith("/"))
            prev += "/";
        return prev + next;
    }

    public String normalize(String address) {
        return address;
    }

    public boolean has(String next) {
        String prev = get();
        String nextResolved = resolv(prev, next);
        return nextResolved != null;
    }

    public boolean has(int relativeIndex) {
        int i = index + relativeIndex;
        return i >= 0 && i < history.size();
    }

    public String go(int relativeIndex) {
        int i = index + relativeIndex;
        if (i < 0 || i >= history.size())
            throw new IndexOutOfBoundsException("absolute index=" + i);
        String prev = get();
        index = i;
        String next = get();
        locationChange(prev, next);
        return next;
    }

    public List<String> list(int begin, int end) {
        if (begin < end) {
            begin += index;
            end += index;
            if (begin < 0)
                begin = 0;
            if (end >= history.size())
                end = history.size();
            int count = end - begin;
            if (count > 0) {
                ArrayList<String> list = new ArrayList<String>(count);
                for (int i = begin; i < end; i++)
                    list.add(history.get(i));
            }
        }
        return new ArrayList<String>(0);
    }

    private List<LocationChangeListener> locationChangeListeners;

    public void addLocationChangeListener(LocationChangeListener listener) {
        if (locationChangeListeners == null)
            locationChangeListeners = new ArrayList<LocationChangeListener>(1);
        locationChangeListeners.add(listener);
    }

    public void removeLocationChangeListener(LocationChangeListener listener) {
        if (locationChangeListeners == null)
            return;
        locationChangeListeners.remove(listener);
    }

    protected void locationChange(String prev, String next) {
        if (locationChangeListeners == null)
            return;
        LocationChangeEvent e = new LocationChangeEvent(this, prev, next);
        for (LocationChangeListener l : locationChangeListeners)
            l.locationChange(e);
    }

    @Override
    public String toString() {
        String current = get();
        return current;
    }

}
