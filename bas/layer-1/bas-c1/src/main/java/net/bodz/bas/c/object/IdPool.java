package net.bodz.bas.c.object;

import java.util.IdentityHashMap;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;

public class IdPool {

    public static final String NULL_ID = "(null)";

    private final Class<?> clazz;
    private int counter;
    private IdentityHashMap<Object, Integer> allocation;

    public IdPool(Class<?> clazz) {
        this.clazz = clazz;
        this.allocation = new IdentityHashMap<>();
    }

    public int next() {
        return ++counter;
    }

    public int getId(Object obj) {
        if (obj == null)
            return 0;
        Integer id = allocation.get(obj);
        if (id == null) {
            id = next();
            allocation.put(obj, id);
        }
        return id;
    }

    public String getSimpleId(Object obj) {
        if (obj == null)
            return NULL_ID;
        int id = getId(obj);
        return id + "@" + obj.getClass().getSimpleName();
    }

    public String getCanonicalId(Object obj) {
        if (obj == null)
            return NULL_ID;
        int id = getId(obj);
        return id + "@" + obj.getClass().getCanonicalName();
    }

    public String getSimpleSystemId(Object obj) {
        if (obj == null)
            return NULL_ID;
        int h = System.identityHashCode(obj);
        String hex = Integer.toHexString(h);
        return hex + "@" + obj.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return clazz + " next id is " + counter;
    }

    public static IdPool getInstance(Class<?> clazz) {
        return clsIdPool.getOrLoad(clazz);
    }

    public static IdPool getInstance() {
        return getInstance(Object.class);
    }

    static final LazyTypeMap<IdPool> clsIdPool;
    static {
        clsIdPool = TypeMapRegistry.createMap(IdPool.class.getName(), //
                new IMapEntryLoader<Class<?>, IdPool>() {
                    @Override
                    public IdPool loadValue(Class<?> type)
                            throws LazyLoadException {
                        return new IdPool(type);
                    }
                });
    }

}
