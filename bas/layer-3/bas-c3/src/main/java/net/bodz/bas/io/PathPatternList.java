package net.bodz.bas.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathPatternList
        implements
            Iterable<IPathPattern> {

    List<IPathPattern> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<IPathPattern> iterator() {
        return list.iterator();
    }

    public boolean add(IPathPattern e) {
        return list.add(e);
    }

    public boolean addBaseName(String baseName) {
        return list.add(new MatchBaseName(baseName));
    }

    public boolean addStartDir(String path) {
        return list.add(new MatchDir(path));
    }

    public boolean addStartDir(String path, int maxDepth) {
        return list.add(new MatchDir(path, maxDepth));
    }

    public boolean allAccept(File dir, String name) {
        return allAccept(dir, name, true);
    }

    public boolean allAccept(File dir, String name, boolean defaultVal) {
        if (isEmpty())
            return defaultVal;
        for (IPathPattern el : list) {
            if (! el.accept(dir, name))
                return false;
        }
        return true;
    }

    public boolean allAccept(File pathname) {
        return allAccept(pathname, true);
    }

    public boolean allAccept(File pathname, boolean defaultVal) {
        if (isEmpty())
            return defaultVal;
        for (IPathPattern el : list) {
            if (! el.accept(pathname))
                return false;
        }
        return true;
    }

    public boolean anyAccept(File dir, String name) {
        return anyAccept(dir, name, false);
    }

    public boolean anyAccept(File dir, String name, boolean defaultVal) {
        if (isEmpty())
            return defaultVal;
        for (IPathPattern el : list) {
            if (el.accept(dir, name))
                return true;
        }
        return false;
    }

    public boolean anyAccept(File pathname) {
        return anyAccept(pathname, false);
    }

    public boolean anyAccept(File pathname, boolean defaultVal) {
        if (isEmpty())
            return defaultVal;
        for (IPathPattern el : list) {
            if (el.accept(pathname))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
