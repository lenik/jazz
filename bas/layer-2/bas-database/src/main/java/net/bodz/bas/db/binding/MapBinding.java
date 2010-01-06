package net.bodz.bas.db.binding;

import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptException;

public class MapBinding {

    private final ScriptClass<?> sclass;
    private final Object instance;

    public MapBinding(Class<?> clazz, Object instance, boolean forceAccess) throws ScriptException {
        assert clazz != null;
        this.sclass = Scripts.convertClass(clazz, forceAccess);
        this.instance = instance;
    }

    public MapBinding(Class<?> clazz, Object instance) throws ScriptException {
        this(clazz, instance, false);
    }

    public MapBinding(Object object, boolean forceAccess) throws ScriptException {
        this(object.getClass(), object, forceAccess);
    }

    public MapBinding(Object object) throws ScriptException {
        this(object, false);
    }

    public void getFields(Map<String, Object> map) throws ScriptException {
        assert map != null;
        for (ScriptField<?> sfield : sclass.getFields()) {
            String name = sfield.getName();
            Object value = sfield.get(instance);
            map.put(name, value);
        }
    }

    public void setFields(Map<String, ?> map) throws ScriptException {
        assert map != null;
        for (Entry<String, ?> e : map.entrySet()) {
            String name = e.getKey();
            Object value = e.getValue();
            ScriptField<Object> sfield = sclass.getField(name);
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
