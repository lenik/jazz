package net.bodz.bas.log;

import java.util.HashMap;
import java.util.Map;

public class LogStore {

    private int                 level;

    private Map<String, LogOut> registry;

    public LogStore(int level) {
        setLevel(level);
    }

    public LogStore(String level) {
        setLevel(level);
    }

    public void reset() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        reset();
    }

    public void setLevel(String level) {
        setLevel(leveldef.get(level));
    }

    public LogOut g(String name) {
        LogOut out = registry.get(name);
        if (out == null) // NullOut
            return LogOuts.nil;
        return out;
    }

    public boolean show(int level) {
        return this.level >= level;
    }

    public boolean show(String level) {
        return show(leveldef.get(level));
    }

    public boolean hide(int level) {
        return this.level < level;
    }

    public boolean hide(String level) {
        return hide(leveldef.get(level));
    }

    protected LogOut getRoot() {
        return LogOuts.stderr;
    }

    protected LogOut create(LogOut parent, String childName) {
        Integer childLevel = leveldef.get(childName);
        if (childLevel == null)
            return null;
        if (childLevel > level)
            return LogOuts.nil;
        if (parent == null)
            return getRoot();
        if (parent == LogOuts.nil)
            return LogOuts.nil;
        return LogOuts.get(parent, childName);
    }

    protected void register(String name, LogOut out) {
        registry.put(name, out);
    }

    private class LogBuilder implements TreeConvert<String, LogOut> {
        @Override
        public LogOut convert(LogOut parent, String child) {
            LogOut childLog = create(parent, child);
            if (childLog != null)
                register(child, childLog);
            return childLog;
        }
    }

    public final void setGraph(Object tree) {
        if (registry == null)
            registry = new HashMap<String, LogOut>();
        else
            ; // registry.clear();
        Trees.convert(null, tree, new LogBuilder());
    }

    protected static final Map<String, Integer> leveldef;
    static {
        leveldef = new HashMap<String, Integer>();
    }

}
