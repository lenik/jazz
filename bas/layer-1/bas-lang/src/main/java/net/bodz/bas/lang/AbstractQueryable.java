package net.bodz.bas.lang;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractQueryable
        implements IQueryable {

    private Map<String, Object> implementations;

    /**
     * Override to this method should call super() at first.
     */
    protected Map<String, Object> loadImplementations() {
        if (implementations == null) {
            implementations = new TreeMap<String, Object>();
            Class<?> chain = getClass();
            while (chain != null) {
                Class<?>[] faces = chain.getInterfaces();
                addThisInterfaces(faces);
                chain = chain.getSuperclass();
            }
        }
        return implementations;
    }

    void addThisInterfaces(Class<?>[] interfaces) {
        for (Class<?> iface : interfaces) {
            String specId = iface.getName();
            if (!implementations.containsKey(specId)) {
                implementations.put(specId, this);
                addThisInterfaces(iface.getInterfaces());
            }
        }
    }

    @Override
    public <T> T query(Class<T> specificationType) {
        if (specificationType == null)
            throw new NullPointerException("specificationType");
        Object impl = query(specificationType.getName());
        // if (impl == null)
        // return null;
        return specificationType.cast(impl);
    }

    @Override
    public Object query(String specificationId) {
        Map<String, Object> impl = loadImplementations();
        return impl.get(specificationId);
    }

    public Map<String, Object> getImplementationMap() {
        Map<String, Object> impl = loadImplementations();
        return Collections.unmodifiableMap(impl);
    }

}
