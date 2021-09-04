package net.bodz.bas.t.map;

import java.util.HashMap;
import java.util.Map;

public class MarksetWithPath
        implements
            IMarksetWithPath {

    final String separator = "/";
    final int separatorLen = separator.length();

    String context = "";
    Map<Object, String> map;

    public MarksetWithPath() {
        this(new HashMap<>());
    }

    public MarksetWithPath(Map<Object, String> idMap) {
        this.map = idMap;
    }

    @Override
    public String getContextPath() {
        return context;
    }

    @Override
    public String path(String name) {
        String path = context;
        if (!path.isEmpty())
            path += separator;
        if (name != null)
            path += name;
        return path;
    }

    @Override
    public boolean containsMark(Object o) {
        return map.containsKey(o);
    }

    @Override
    public String addMark(Object o, String name) {
        String oldPath = map.get(o);
        if (oldPath != null)
            return oldPath;
        map.put(o, path(name));
        return null;
    }

    @Override
    public boolean removeMark(Object o) {
        boolean exists = map.containsKey(o);
        map.remove(o);
        return exists;
    }

    @Override
    public void clearMarks() {
        map.clear();
    }

    @Override
    public void enter(String name) {
        if (name == null)
            throw new NullPointerException("name");
        if (context.isEmpty())
            context = name;
        else
            context = context + separator + name;
    }

    @Override
    public void leave() {
        if (context.isEmpty())
            throw new IllegalStateException("leave from the outmost.");
        int last = context.lastIndexOf(separator);
        if (last != -1)
            context = context.substring(0, last);
        else
            context = "";
    }

    @Override
    public String lookup(Object o) {
        return map.get(o);
    }

}
