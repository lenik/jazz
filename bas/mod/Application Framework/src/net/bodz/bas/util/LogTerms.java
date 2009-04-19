package net.bodz.bas.util;

import net.bodz.bas.lang.Caller;
import net.bodz.bas.types.PrefixMap;
import net.bodz.bas.types.TypeHierMap;

public class LogTerms {

    public static final LogTerm console;
    static {
        console = new ConsoleLogTerm();
    }

    static PrefixMap<LogTerm>   nameMap;
    static TypeHierMap<LogTerm> typeMap;
    static {
        nameMap = new PrefixMap<LogTerm>();
        nameMap.put("", console);
        typeMap = new TypeHierMap<LogTerm>();
        typeMap.put(Object.class, console);
    }

    public static LogTerm get(String name) {
        LogTerm t = nameMap.floor(name);
        assert t != null : "null by name " + name;
        return t;
    }

    public static void set(String name, LogTerm logTerm) {
        nameMap.put(name, logTerm);
    }

    public static LogTerm get(int caller) {
        Class<?> callerClass = Caller.getCallerClass(1 + caller);
        return get(callerClass);
    }

    public static void set(int caller, LogTerm logTerm) {
        Class<?> callerClass = Caller.getCallerClass(1 + caller);
        set(callerClass, logTerm);
    }

    public static LogTerm get(Class<?> type) {
        LogTerm t = typeMap.floor(type);
        assert t != null : "null by type " + type;
        return t;
    }

    public static void set(Class<?> type, LogTerm logTerm) {
        if (logTerm == null)
            throw new NullPointerException("logTerm");
        typeMap.put(type, logTerm);
    }

}
