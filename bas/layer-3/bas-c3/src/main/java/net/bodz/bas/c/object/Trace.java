package net.bodz.bas.c.object;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.bodz.bas.c.object.IdentityObjectMap;
import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.c.object.RelationMap;
import net.bodz.bas.c.object.RelationMapManager;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.impl.BufferedLogger;

/**
 * Keep trace information for debug.
 */
public class Trace {

    public static boolean enabled = SystemProperties.isDevelopMode();

    static RelationMapManager tracedRelationMaps = new RelationMapManager();
    static IdentityObjectMap<BufferedLogger> objectLoggerMap = new IdentityObjectMap<BufferedLogger>();

    public static RelationMap getObjectRelationMap(Object obj) {
        return tracedRelationMaps.get(obj);
    }

    public static void link(Object from, String relation, Object... dests) {
        RelationMap map = getObjectRelationMap(from);
        map.link(relation, dests);
    }

    public static void unlink(Object from, String relation, Object... dests) {
        RelationMap map = getObjectRelationMap(from);
        map.unlink(relation, dests);
    }

    public static Logger getObjectLogger(Object obj) {
        BufferedLogger logger = objectLoggerMap.get(obj);
        if (logger == null) {
            logger = new BufferedLogger("    ");
            objectLoggerMap.put(obj, logger);
        }
        return logger;
    }

    public static void trace(Object obj, Object... messages) {
        Logger logger = getObjectLogger(obj);
        logger.trace(messages);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new DumpThread());
    }

    static class DumpThread
            extends Thread {

        PrintStream out = System.out;
        PrintStream err = System.out;

        @Override
        public void run() {
            out.println("Logs: ");
            for (Map.Entry<Object, BufferedLogger> entry : objectLoggerMap.entrySet()) {
                final Object object = entry.getKey();
                final BufferedLogger logs = entry.getValue();
                String name = ObjectInfo.getSimpleId(object);
                out.println("  Object: " + name);
                logs.dump(out, err);
            }
            out.println();

            out.println("Relations: ");
            for (Map.Entry<Object, RelationMap> entry : tracedRelationMaps.entrySet()) {
                final Object object = entry.getKey();
                final RelationMap relationMap = entry.getValue();
                String name = ObjectInfo.getSimpleId(object);
                out.println("  Object: " + name);
                for (Map.Entry<String, IdentityObjectSet> relationEntry : relationMap.entrySet()) {
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
                        out.println(ObjectInfo.getSimpleId(dest0));
                    } else {
                        out.println(" -> ");
                        while (iter.hasNext()) {
                            Object dest = iter.next();
                            // out.print("     -> ");
                            out.print("          ");
                            out.println(ObjectInfo.getSimpleId(dest));
                        }
                    }
                } // for relationEntry
                out.println();
            } // for allRelations
            out.println();
        } // run

    } // DumpThread

}
