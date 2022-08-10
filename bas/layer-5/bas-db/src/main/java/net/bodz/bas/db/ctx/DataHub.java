package net.bodz.bas.db.ctx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeMap;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.decl.Namespace;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.order.PriorityComparator;

/**
 * Marker class.
 */
@Priority
@Namespace
@IndexedType(includeAbstract = false)
public abstract class DataHub
        implements
            IDataHub,
            IPriority {

    int priority = Priority.DEFAULT;

    public DataHub() {
        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            priority = aPriority.value();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    protected static final Map<String, ConnectOptions> nameMap = new TreeMap<>();

    protected static ConnectOptions declare(String name) {
        ConnectOptions o = new ConnectOptions();
        nameMap.put(name, o);
        return o;
    }

    private static List<DataHub> hubs;

    synchronized static void loadHubs() {
        if (hubs == null) {
            hubs = new ArrayList<>();
            for (DataHub hub : ServiceLoader.load(DataHub.class))
                hubs.add(hub);
            Collections.sort(hubs, PriorityComparator.INSTANCE);
        }
    }

    public static DataHub getPreferredHub() {
        loadHubs();
        if (hubs.isEmpty())
            return null;
        else
            return hubs.get(0);
    }

}
