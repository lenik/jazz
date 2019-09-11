package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypeExtras {

    private final Class<?> type;
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private Map<Class<?>, Object> features = new HashMap<Class<?>, Object>();

    public TypeExtras(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public Map<String, Object> getAttributeMap() {
        return attributes;
    }

    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public <T> T getFeature(Class<T> featureClass) {
        Object value = features.get(featureClass);
        return featureClass.cast(value);
    }

    public <T> void setFeature(Class<T> featureClass, T implementation) {
        if (featureClass == null)
            throw new NullPointerException("featureClass");
        if (implementation == null)
            throw new NullPointerException("implementation");
        features.put(featureClass, implementation);
    }

    public void removeFeature(Class<?> featureClass) {
        features.remove(featureClass);
    }

    static final LazyTypeMap<TypeExtras> clsExtras;
    static {
        clsExtras = TypeMapRegistry.createMap(TypeExtras.class);
    }

    public static TypeExtras of(Class<?> clazz) {
        TypeExtras extras = clsExtras.getOrLoad(clazz);
        return extras;
    }

}
