package net.bodz.bas.lang.script;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Types;

class _ScriptClass<T> implements ScriptClass<T> {

    // private Class<T> clazz;
    private _ScriptClass<?>              next;
    private Map<String, ScriptField<?>>  fields;
    private Map<String, ScriptMethod<?>> methods;

    public _ScriptClass(Class<T> clazz) throws ScriptException {
        assert clazz != null;
        // this.clazz = clazz;
        Class<?> _super = clazz.getSuperclass();
        if (_super != null)
            next = (_ScriptClass<?>) Scripts.convertClass(_super);

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new ScriptException(e.getMessage(), e);
        }

        importFields(clazz.getDeclaredFields());

        // bean properties overwrite fields
        importProperties(beanInfo.getPropertyDescriptors());

        // importMethods(clazz.getDeclaredMethods());

        importMethods(beanInfo.getMethodDescriptors());
    }

    protected void importFields(Field... fields) {
        for (final Field field : fields) {
            ScriptField<Object> sfield = new ScriptField<Object>() {
                @SuppressWarnings("unchecked")
                @Override
                public Class<Object> getType() {
                    return (Class<Object>) field.getType();
                }

                @Override
                public Object get(Object object) throws ScriptException {
                    try {
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    }
                }

                @Override
                public void set(Object object, Object value)
                        throws ScriptException {
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    }
                }
            };
            putField(field.getName(), sfield);
        }
    }

    protected void importProperties(PropertyDescriptor... properties) {
        for (final PropertyDescriptor property : properties) {
            final Method read = property.getReadMethod();
            final Method write = property.getWriteMethod();
            ScriptField<?> sfield = new ScriptField<Object>() {

                @SuppressWarnings("unchecked")
                @Override
                public Class<Object> getType() {
                    return (Class<Object>) property.getPropertyType();
                }

                @Override
                public Object get(Object object) throws ScriptException {
                    if (read == null)
                        throw new UnsupportedOperationException();
                    try {
                        return read.invoke(object);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        throw new ScriptException(e.getMessage(), e);
                    }
                }

                @Override
                public void set(Object object, Object value)
                        throws ScriptException {
                    if (write == null)
                        throw new ReadOnlyException();
                    try {
                        /**
                         * list = { value, indexes... } <code>
                        if (indexes.length > 0) {
                            Object[] list = new Object[indexes.length + 1];
                            System.arraycopy(indexes, 0, list, 0,
                                    indexes.length);
                            list[indexes.length] = value;
                            write.invoke(object, list);
                        }
                         */
                        write.invoke(object, value);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        throw new ScriptException(e.getMessage(), e);
                    }
                }
            };
            putField(property.getName(), sfield);
        }
    }

    protected void importMethods(Method... methods) {
        final TreeSet<Object> sorted = new TreeSet<Object>(Comparators.METHOD);
        for (Method method : methods)
            sorted.add(method);
        importMethods(sorted);
    }

    protected void importMethods(MethodDescriptor... methods) {
        final TreeSet<Object> sorted = new TreeSet<Object>(Comparators.METHOD);
        for (MethodDescriptor method : methods) {
            sorted.add(method.getMethod());
        }
        importMethods(sorted);
    }

    protected void importMethods(final TreeSet<Object> sorted) {
        String lastName = null;
        for (Object o : sorted) {
            final Method method = (Method) o;
            final String name = method.getName();
            if (name.equals(lastName))
                // methods with same name only wrapped once
                continue;

            ScriptMethod<Object> smethod = new ScriptMethod<Object>() {
                @SuppressWarnings("unchecked")
                @Override
                public Class<Object> getReturnType() {
                    return (Class<Object>) method.getReturnType();
                }

                @Override
                public Class<?>[] getParameterTypes() {
                    return method.getParameterTypes();
                }

                @Override
                public Object invoke(Object object, Object... parameters)
                        throws IllegalArgumentException, ScriptException {
                    Class<?>[] ptypes = Types.getTypes(parameters);
                    MethodSignature sig = new MethodSignature(name, ptypes);
                    Method m = (Method) sorted.floor(sig);
                    for (; m != null; m = (Method) sorted.higher(m)) {
                        int cmp = m.getName().compareTo(name);
                        if (cmp < 0)
                            continue;
                        if (cmp > 0)
                            break;
                        Class<?>[] pt = m.getParameterTypes();
                        cmp = pt.length - ptypes.length;
                        if (cmp < 0)
                            continue;
                        if (cmp > 0)
                            break;
                        // using type adapter here
                        try {
                            return m.invoke(object, parameters);
                        } catch (IllegalAccessException e) {
                            throw new ScriptException(e.getMessage(), e);
                        } catch (InvocationTargetException e) {
                            throw new ScriptException(e.getMessage(), e);
                        }
                    }
                    throw new UnsupportedOperationException(
                            new NoSuchMethodException());
                }
            };
            putMethod(method.getName(), smethod);
        }
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
    public String getName() {
        throw new NotImplementedException();
    }

    @Override
    public _ScriptClass<?> getSuperClass() {
        return next;
    }

    @Override
    public ScriptField<?>[] getFields() {
        throw new NotImplementedException();
    }

    @Override
    public ScriptField<?> getField(String name) {
        _ScriptClass<?> cc = this;
        while (cc != null) {
            ScriptField<?> sfield = cc.fields.get(name);
            if (sfield != null)
                return sfield;
            cc = cc.getSuperClass();
        }
        return null;
    }

    @Override
    public boolean hasField(String name) {
        return getField(name) != null;
    }

    @Override
    public ScriptMethod<?>[] getMethods() {
        throw new NotImplementedException();
    }

    @Override
    public ScriptMethod<?> getMethod(String name) {
        _ScriptClass<?> cc = this;
        while (cc != null) {
            ScriptMethod<?> smethod = cc.methods.get(name);
            if (smethod != null)
                return smethod;
            cc = cc.getSuperClass();
        }
        return null;
    }

    @Override
    public boolean hasMethod(String name) {
        return getMethod(name) != null;
    }

    @Override
    public T cast(Object obj) {
        throw new NotImplementedException();
    }

}
