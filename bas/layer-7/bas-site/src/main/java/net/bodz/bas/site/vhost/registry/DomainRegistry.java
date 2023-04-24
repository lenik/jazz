package net.bodz.bas.site.vhost.registry;

import java.util.Map;

import net.bodz.bas.repr.form.SortOrder;

public class DomainRegistry<T>
        implements
            IDomainRegistry<T> {

    boolean nullable = true;

    T _default;
    Map<String, T> suffixMap;
    Map<String, T> map;

    public DomainRegistry() {
        this(SortOrder.NONE);
    }

    DomainRegistry(SortOrder order) {
        suffixMap = order.newMap();
        map = order.newMap();
    }

    @Override
    public boolean containsDomain(String domain) {
        if (_default != null)
            return true; // always fallback
        if (domain == null)
            return false;
        if (map.containsKey(domain))
            return true;
        while (true) {
            if (suffixMap.containsKey(domain))
                return true;
            int dot = domain.indexOf('.');
            if (dot != -1)
                domain = domain.substring(dot + 1);
            else
                return false;
        }
    }

    @Override
    public T findDomain(String domain) {
        if (domain == null)
            return _default;

        T obj = map.get(domain);
        if (obj != null)
            return obj;
        else if (nullable && map.containsKey(domain))
            return null;

        while (true) {
            obj = suffixMap.get(domain);
            if (obj != null)
                return obj;
            else if (nullable & suffixMap.containsKey(domain))
                return null;

            int dot = domain.indexOf('.');
            if (dot != -1)
                domain = domain.substring(dot + 1);
            else
                return _default;
        }
    }

    @Override
    public void setDefault(T obj) {
        _default = obj;
    }

    @Override
    public void setDomain(String domain, T obj) {
        map.put(domain, obj);
    }

    @Override
    public void setDomainSuffix(String suffix, T obj) {
        suffixMap.put(suffix, obj);
    }

    @Override
    public boolean addDefault(T obj) {
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        if (_default != null)
            return false;
        _default = obj;
        return true;
    }

    @Override
    public boolean addDomain(String domain, T obj) {
        if (domain == null)
            throw new NullPointerException("domain");
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        if (map.containsKey(domain))
            return false;
        map.put(domain, obj);
        return true;
    }

    @Override
    public boolean addDomainSuffix(String suffix, T obj) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        if (suffixMap.containsKey(suffix))
            return false;
        suffixMap.put(suffix, obj);
        return true;
    }

    @Override
    public T removeDomain(String domain) {
        return map.remove(domain);
    }

    @Override
    public T removeDomainSuffix(String suffix) {
        return suffixMap.remove(suffix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1000);
        if (_default != null)
            sb.append("default: " + _default + "\n");
        for (String suffix : suffixMap.keySet())
            sb.append("suffix " + suffix + ": " + suffixMap.get(suffix) + "\n");
        for (String domain : map.keySet())
            sb.append("domain " + domain + ": " + map.get(domain) + "\n");
        return sb.toString();
    }

}
