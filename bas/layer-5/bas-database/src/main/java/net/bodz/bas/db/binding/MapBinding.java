package net.bodz.bas.db.binding;

import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptException;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IPropertyMap;
import net.bodz.bas.trait.Traits;

public class MapBinding {

    private final IPropertyMap propertyMap;
    private final Object instance;

    public MapBinding(Class<?> clazz, Object instance, boolean forceAccess)
            throws ScriptException {
        assert clazz != null;
        this.propertyMap = Traits.getTrait(clazz, IPropertyMap.class);
        this.instance = instance;
    }

    public MapBinding(Class<?> clazz, Object instance)
            throws ScriptException {
        this(clazz, instance, false);
    }

    public MapBinding(Object object, boolean forceAccess)
            throws ScriptException {
        this(object.getClass(), object, forceAccess);
    }

    public MapBinding(Object object)
            throws ScriptException {
        this(object, false);
    }

    public void getFields(Map<String, Object> map)
            throws ReflectiveOperationException {
        assert map != null;
        for (IProperty property : propertyMap.getProperties()) {
            String name = property.getName();
            Object value = property.getValue(instance);
            map.put(name, value);
        }
    }

    public void setFields(Map<String, ?> map)
            throws ReflectiveOperationException {
        assert map != null;
        for (Entry<String, ?> e : map.entrySet()) {
            String name = e.getKey();
            Object value = e.getValue();
            IProperty property = propertyMap.getProperty(name);
            if (property == null)
                setNonexistField(name, value);
            else
                property.setValue(instance, value);
        }
    }

    protected Object getNonexistField(String name) {
        throw new NoSuchFieldError(name);
    }

    protected void setNonexistField(String name, Object value) {
        throw new NoSuchFieldError(name);
    }

}
