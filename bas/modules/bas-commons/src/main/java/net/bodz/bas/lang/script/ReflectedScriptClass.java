package net.bodz.bas.lang.script;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeSet;

import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Types;

class ReflectedScriptClass<T> extends _ScriptClass<T> {

    private boolean forceAccess;

    public ReflectedScriptClass(Class<T> origClass) throws ScriptException {
        this(origClass, false);
    }

    public ReflectedScriptClass(Class<T> origClass, boolean forceAccess) throws ScriptException {
        super(origClass, _get(origClass.getSuperclass(), forceAccess));
        this.forceAccess = forceAccess;

        load();
    }

    protected void load() throws ScriptException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(origClass);
        } catch (IntrospectionException e) {
            throw new ScriptException(e.getMessage(), e);
        }

        importFields(origClass.getDeclaredFields());

        // bean properties overwrite fields
        importProperties(beanInfo.getPropertyDescriptors());

        // importMethods(clazz.getDeclaredMethods());

        importMethods(beanInfo.getMethodDescriptors());
    }

    protected void importFields(Field... fields) {
        for (final Field field : fields) {
            ScriptField<Object> sfield = new ScriptField<Object>() {
                @Override
                public String getName() {
                    return field.getName();
                }

                @SuppressWarnings("unchecked")
                @Override
                public Class<Object> getType() {
                    return (Class<Object>) field.getType();
                }

                @Override
                public Object get(Object object) throws ScriptException {
                    boolean orig = field.isAccessible();
                    try {
                        if (forceAccess && !orig)
                            field.setAccessible(true);
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    } finally {
                        if (forceAccess && !orig)
                            field.setAccessible(false);
                    }
                }

                @Override
                public void set(Object object, Object value) throws ScriptException {
                    boolean orig = field.isAccessible();
                    try {
                        if (forceAccess && !orig)
                            field.setAccessible(true);
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        throw new ScriptException(e.getMessage(), e);
                    } finally {
                        if (forceAccess && !orig)
                            field.setAccessible(false);
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
                @Override
                public String getName() {
                    return property.getName();
                }

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
                public void set(Object object, Object value) throws ScriptException {
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
                public Object invoke(Object object, Object... parameters) throws IllegalArgumentException,
                        ScriptException {
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
                        // TODO - using type adapter here
                        boolean orig = m.isAccessible();
                        try {
                            if (forceAccess && !orig)
                                m.setAccessible(true);
                            return m.invoke(object, parameters);
                        } catch (IllegalAccessException e) {
                            throw new ScriptException(e.getMessage(), e);
                        } catch (InvocationTargetException e) {
                            throw new ScriptException(e.getMessage(), e);
                        } finally {
                            if (forceAccess && !orig)
                                m.setAccessible(false);
                        }
                    }
                    throw new UnsupportedOperationException(new NoSuchMethodException());
                }
            };
            putMethod(method.getName(), smethod);
        }
    }

    @SuppressWarnings("unchecked")
    private static <R> ReflectedScriptClass<R> _get(Class<R> clazz, boolean forceAccess) throws ScriptException {
        if (clazz == null)
            return null;
        return (ReflectedScriptClass<R>) Scripts.convertClass(clazz, forceAccess);
    }

}
