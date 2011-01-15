package net.bodz.bas.db.binding;

import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptException;

import net.bodz.bas.potato.IPotatoProperty;
import net.bodz.bas.potato.IPotatoType;
import net.bodz.bas.potato.PotatoException;
import net.bodz.bas.potato.adapter.reflect.ReflectPotatoType;

public class MapBinding {

    private final IPotatoType<?> sclass;
    private final Object instance;

    public MapBinding(Class<?> clazz, Object instance, boolean forceAccess)
            throws ScriptException {
        assert clazz != null;
        this.sclass = ReflectPotatoType.getPotatoType(clazz);
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
            throws PotatoException {
        assert map != null;
        for (IPotatoProperty property : sclass.getProperties()) {
            String name = property.getName();
            Object value = property.get(instance);
            map.put(name, value);
        }
    }

    public void setFields(Map<String, ?> map)
            throws PotatoException {
        assert map != null;
        for (Entry<String, ?> e : map.entrySet()) {
            String name = e.getKey();
            Object value = e.getValue();
            IPotatoProperty sfield = sclass.getProperty(name);
            if (sfield == null)
                setNonexistField(name, value);
            else
                sfield.set(instance, value);
        }
    }

    protected Object getNonexistField(String name) {
        throw new NoSuchFieldError(name);
    }

    protected void setNonexistField(String name, Object value) {
        throw new NoSuchFieldError(name);
    }

}
