package net.bodz.bas.commons.scripting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.exceptions.NotImplementedException;

public abstract class _ScriptClass<T> implements ScriptClass<T> {

    protected final Class<?> origClass;
    private final ScriptClass<?> superScriptClass;

    private Map<String, ScriptField<?>> fields;
    private Map<String, ScriptMethod<?>> methods;

    public _ScriptClass(Class<?> origClass, ScriptClass<?> superScriptClass) {
        this.origClass = origClass;
        this.superScriptClass = superScriptClass;
    }

    public _ScriptClass(Class<?> origClass) {
        this(origClass, null);
    }

    @Override
    public String getName() {
        throw new NotImplementedException();
    }

    @Override
    public ScriptClass<?> getSuperClass() {
        return superScriptClass;
    }

    @Override
    public T cast(Object obj) {
        throw new NotImplementedException();
    }

    private static final ScriptField<?>[] ScriptField_0;
    private static final ScriptMethod<?>[] ScriptMethod_0;
    static {
        ScriptField_0 = new ScriptField<?>[0];
        ScriptMethod_0 = new ScriptMethod<?>[0];
    }

    @Override
    public ScriptField<?>[] getFields() {
        if (fields == null)
            return ScriptField_0;
        Collection<ScriptField<?>> sfields = fields.values();
        return sfields.toArray(ScriptField_0);
    }

    @Override
    public ScriptMethod<?>[] getMethods() {
        if (methods == null)
            return ScriptMethod_0;
        Collection<ScriptMethod<?>> smethods = methods.values();
        return smethods.toArray(ScriptMethod_0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <F> ScriptField<F> getField(String name) {
        if (fields != null) {
            ScriptField<?> sfield = fields.get(name);
            if (sfield != null)
                return (ScriptField<F>) sfield;
        }
        ScriptClass<?> _super = getSuperClass();
        if (_super == null)
            return null;
        return _super.getField(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> ScriptMethod<R> getMethod(String name) {
        if (methods != null) {
            ScriptMethod<?> smethod = methods.get(name);
            if (smethod != null)
                return (ScriptMethod<R>) smethod;
        }
        ScriptClass<?> _super = getSuperClass();
        if (_super == null)
            return null;
        return _super.getMethod(name);
    }

    @Override
    public boolean hasField(String name) {
        if (fields == null)
            return false;
        return getField(name) != null;
    }

    @Override
    public boolean hasMethod(String name) {
        if (methods == null)
            return false;
        return getMethod(name) != null;
    }

    protected void putField(String name, ScriptField<?> sfield) {
        if (fields == null)
            fields = new HashMap<String, ScriptField<?>>();
        fields.put(name, sfield);
    }

    protected void putMethod(String name, ScriptMethod<?> smethod) {
        if (methods == null)
            methods = new HashMap<String, ScriptMethod<?>>();
        methods.put(name, smethod);
    }

    @Override
    public Object get(Object object, String fieldName) throws ScriptException {
        ScriptField<Object> field = getField(fieldName);
        if (field == null)
            throw new ScriptException("no such field: " + fieldName); 
        return field.get(object);
    }

    @Override
    public void set(Object object, String fieldName, Object newValue) throws ScriptException {
        ScriptField<Object> field = getField(fieldName);
        if (field == null)
            throw new ScriptException("no such field: " + fieldName); 
        field.set(object, newValue);
    }

    @Override
    public Object invoke(Object object, String methodName, Object... parameters) throws ScriptException {
        ScriptMethod<Object> method = getMethod(methodName);
        if (method == null)
            throw new ScriptException("no such method: " + methodName); 
        return method.invoke(object, parameters);
    }

}
