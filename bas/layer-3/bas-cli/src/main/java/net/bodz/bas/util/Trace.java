package net.bodz.bas.util;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.text.util.StringArray;

/**
 * @test {@link TraceTest}
 */
public class Trace {

    public static boolean enabled = SystemProperties.isDevelopMode();
    public static Object logPrefix = "";

    public static void setLogDate(boolean enabled) {
        if (enabled)
            setLogDate("yy/MM/dd HH:mm:ss.SSS");
        else
            logPrefix = "";
    }

    public static void setLogDate(String datePattern) {
        final DateFormat format = new SimpleDateFormat(datePattern);
        logPrefix = new Object() {
            @Override
            public String toString() {
                String s = format.format(new Date());
                return "[" + s + "] ";
            }
        };
    }

    static class RelationMap
            extends TreeMap<String, Collection<Object>> {

        private static final long serialVersionUID = -1723440822997163551L;

    }

    static final Map<Class<?>, Integer> typeNextId;
    static final IdentityHashMap<Object, Integer> allId;

    static {
        typeNextId = new HashMap<Class<?>, Integer>();
        allId = new IdentityHashMap<Object, Integer>();
    }

    public static int getId(Object object) {
        if (object == null)
            return 0;
        Integer id = allId.get(object);
        if (id == null) {
            Class<?> type = object.getClass();
            Integer nextId = typeNextId.get(type);
            if (nextId == null) {
                id = 0;
                nextId = 1;
            } else {
                id = nextId++;
            }
            typeNextId.put(type, nextId);
            allId.put(object, id);
        }
        return id;
    }

    public static String getName(Object object) {
        if (object == null)
            return "null";
        int id = getId(object);
        String name = object.getClass().getName() + "#" + id;
        return name;
    }

    public static String getString(Object object) {
        String s = String.valueOf(object);
        if (object == null)
            return s;
        String name = getName(object);
        return name + ": " + object;
    }

    static boolean showString = true;

    public static String getNameOrString(Object object) {
        if (showString)
            return getString(object);
        else
            return getName(object);
    }

    static final IdentityHashMap<Object, List<String>> allLogs;
    static final IdentityHashMap<Object, RelationMap> allRelations;

    static {
        allLogs = new IdentityHashMap<Object, List<String>>();
        allRelations = new IdentityHashMap<Object, RelationMap>();
        if (enabled)
            Runtime.getRuntime().addShutdownHook(new DumpThread());
    }

    static List<String> getList(Object object) {
        if (object == null)
            throw new NullPointerException("object");
        List<String> list = allLogs.get(object);
        if (list == null) {
            list = new ArrayList<String>();
            allLogs.put(object, list);
        }
        return list;
    }

    /**
     * Print log
     */
    public static void p(Object object, Object... mesg) {
        if (enabled) {
            if (object == null)
                return;
            List<String> list = getList(object);
            String s = StringArray.join("", mesg);
            list.add(logPrefix + s);
        }
    }

    /**
     * Continue the last log
     */
    public static void c(Object object, Object... mesg) {
        if (enabled) {
            if (object == null)
                return;
            List<String> list = getList(object);
            String s = StringArray.join("", mesg);
            if (list.isEmpty()) {
                list.add(logPrefix + s);
            } else {
                int last = list.size() - 1;
                list.set(last, list.get(last) + s);
            }
        }
    }

    /**
     * Log using format
     */
    public static void f(Object object, String format, Object... args) {
        if (enabled) {
            if (object == null)
                return;
            List<String> list = getList(object);
            String s = String.format(format, args);
            list.add(logPrefix + s);
        }
    }

    static RelationMap getRelationMap(Object object) {
        if (object == null)
            throw new NullPointerException("object");
        RelationMap relationMap = allRelations.get(object);
        if (relationMap == null) {
            relationMap = new RelationMap();
            allRelations.put(object, relationMap);
        }
        return relationMap;
    }

    public static Collection<Object> getRelations(Object object, String relation) {
        RelationMap relationMap = getRelationMap(object);
        Collection<Object> collection = relationMap.get(relation);
        return collection;
    }

    public static void setRelations(Object object, String relation, Collection<Object> collection) {
        if (enabled) {
            if (object == null)
                return;
            RelationMap relationMap = getRelationMap(object);
            relationMap.put(relation, collection);
        }
    }

    public static void link(Object src, String relation, Object... dests) {
        if (enabled) {
            if (src == null)
                return;
            RelationMap relationMap = getRelationMap(src);
            Collection<Object> collection = relationMap.get(relation);
            if (collection == null) {
                collection = new IdentSet();
                relationMap.put(relation, collection);
            }
            for (Object dest : dests)
                collection.add(dest);
        }
    }

    public static boolean unlink(Object src, String relation, Object... dests) {
        if (enabled) {
            if (src == null)
                return false;
            RelationMap relationMap = allRelations.get(src);
            if (relationMap != null) {
                Collection<Object> collection = relationMap.get(relation);
                if (collection != null) {
                    boolean dirty = false;
                    for (Object dest : dests)
                        dirty |= collection.remove(dest);
                    if (collection.isEmpty()) {
                        relationMap.remove(relation);
                        if (relationMap.isEmpty())
                            allRelations.remove(src);
                    }
                    return dirty;
                }
            }
        }
        return false;
    }

    static class DumpThread
            extends Thread {

        PrintStream out = System.out;

        @Override
        public void run() {
            out.println("Logs: ");
            for (Map.Entry<Object, List<String>> entry : allLogs.entrySet()) {
                final Object object = entry.getKey();
                final List<String> logs = entry.getValue();
                String name = Trace.getNameOrString(object);
                out.println("  Object: " + name);
                for (String log : logs)
                    out.println("    " + log);
            }
            out.println();

            out.println("Relations: ");
            for (Map.Entry<Object, RelationMap> entry : allRelations.entrySet()) {
                final Object object = entry.getKey();
                final RelationMap relationMap = entry.getValue();
                String name = Trace.getNameOrString(object);
                out.println("  Object: " + name);
                for (Map.Entry<String, Collection<Object>> relationEntry : relationMap.entrySet()) {
                    final String relation = relationEntry.getKey();
                    final Collection<Object> collection = relationEntry.getValue();
                    out.print("      ");
                    out.print(relation);
                    int n = collection.size();
                    Iterator<Object> iter = collection.iterator();
                    if (n == 0)
                        out.println(" -> (none)");
                    else if (n == 1) {
                        Object dest0 = iter.next();
                        out.print(" -> ");
                        out.println(Trace.getNameOrString(dest0));
                    } else {
                        out.println(" -> ");
                        while (iter.hasNext()) {
                            Object dest = iter.next();
                            // out.print("     -> ");
                            out.print("          ");
                            out.println(Trace.getNameOrString(dest));
                        }
                    }
                } // for relationEntry
                out.println();
            } // for allRelations
            out.println();
        } // run

    } // DumpThread

}
