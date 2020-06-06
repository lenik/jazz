package net.bodz.bas.db.ctx;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.db.jdbc.ConnectOptions;

/**
 * Marker class.
 */
public abstract class DataContexts {

    static final Map<String, ConnectOptions> nameMap = new TreeMap<>();

    protected static ConnectOptions declare(String name) {
        ConnectOptions o = new ConnectOptions();
        nameMap.put(name, o);
        return o;
    }

}
