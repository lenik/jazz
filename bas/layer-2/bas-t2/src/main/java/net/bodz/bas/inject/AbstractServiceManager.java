package net.bodz.bas.inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.rtx.Injector;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.order.PriorityComparator;

public abstract class AbstractServiceManager<T> {

    Class<T> serviceClass;
    Injector injector;

    Collection<T> providers;
    Map<String, T> nameMap = new LinkedHashMap<>();

    public AbstractServiceManager(Class<T> serviceClass, Injector injector) {
        if (serviceClass == null)
            throw new NullPointerException("serviceClass");
        if (injector == null)
            throw new NullPointerException("injector");

        this.serviceClass = serviceClass;
        this.injector = injector;

        if (IPriority.class.isAssignableFrom(serviceClass)) {
            providers = new TreeSet<T>(PriorityComparator.getInstance());
        } else {
            providers = new ArrayList<T>();
        }

        load();
    }

    public boolean isNamed() {
        return false;
    }

    public String getName(T provider) {
        return null;
    }

    protected void load()
            throws LoadException {
        for (Class<? extends T> type : IndexedTypes.list(serviceClass, false)) {
            T provider = injector.instantiate(type);

            String name = getName(provider);
            if (name != null) {
                T prev = nameMap.get(name);
                if (prev != null)
                    throw new DuplicatedKeyException(name, prev);
                nameMap.put(name, provider);
            }

            providers.add(provider);
        }
    }

    public Collection<T> getProviders() {
        return providers;
    }

    public T getProvider(String name) {
        return nameMap.get(name);
    }

    public T getFirstProvider(String... names) {
        for (String name : names)
            if (name != null) {
                T provider = getProvider(name);
                if (provider != null)
                    return provider;
            }
        return null;
    }

}
