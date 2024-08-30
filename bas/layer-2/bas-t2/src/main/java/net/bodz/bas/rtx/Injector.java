package net.bodz.bas.rtx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.NotNull;

public class Injector {

    /**
     * check for single constructor mode.
     */
    boolean explicitConstructor = true;
    boolean requireAll;

    TypePoMap<Object> typedProvides;
    Map<String, Object> namedProvides;

    Map<Class<?>, Object> typeDefaults = _typeDefaults;

    static Map<Class<?>, Object> _typeDefaults = new HashMap<>();
    static {
        _typeDefaults.put(byte.class, (byte) 0);
        _typeDefaults.put(short.class, (short) 0);
        _typeDefaults.put(int.class, 0);
        _typeDefaults.put(long.class, 0L);
        _typeDefaults.put(float.class, 0.0f);
        _typeDefaults.put(double.class, 0.0d);
        _typeDefaults.put(boolean.class, false);
        _typeDefaults.put(char.class, '\0');
    }

    public Injector() {
        typedProvides = new TypePoMap<>();
        namedProvides = new HashMap<>();
    }

    public Injector(TypePoMap<Object> typedProvides, Map<String, Object> namedProvides) {
        this.typedProvides = typedProvides;
        this.namedProvides = namedProvides;
    }

    public boolean isExplicitConstructor() {
        return explicitConstructor;
    }

    public void setExplicitConstructor(boolean explicitConstructor) {
        this.explicitConstructor = explicitConstructor;
    }

    public boolean isRequireAll() {
        return requireAll;
    }

    public void setRequireAll(boolean requireAll) {
        this.requireAll = requireAll;
    }

    @SuppressWarnings("unchecked")
    public <T> void provide(T o) {
        if (o == null)
            throw new NullPointerException("o");
        Class<? super T> type = (Class<? super T>) o.getClass();
        provide(type, o);
    }

    public <T> void provide(Class<? super T> clazz, T val) {
        typedProvides.put(clazz, val);
    }

    public void provide(String name, Object val) {
        namedProvides.put(name, val);
    }

    boolean isRequired(Parameter p) {
        if (requireAll)
            return true;
        if (p.isAnnotationPresent(NotNull.class))
            return true;
        return false;
    }

    public <T> T instantiate(Class<T> type)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?>[] ctors = type.getDeclaredConstructors();

        if (explicitConstructor) {
            if (ctors.length > 1) {
                Constructor<?> defaultCtor = null;
                for (Constructor<?> ctor : ctors)
                    if (ctor.getParameterCount() == 0) {
                        defaultCtor = ctor;
                        break;
                    }
                if (defaultCtor == null)
                    throw new IllegalUsageException("type " + type + " has more than one constructor.");
                else
                    ctors = new Constructor<?>[] { defaultCtor };
            }
            if (ctors.length == 0)
                throw new IllegalUsageException("type " + type + " has no constructor.");
        }

        C: for (Constructor<?> ctor : ctors) {
            int n = ctor.getParameterCount();
            Parameter[] parameters = ctor.getParameters();
            Object[] paramValues = new Object[n];

            for (int i = 0; i < n; i++) {
                Parameter p = parameters[i];
                Class<?> paramType = p.getType();
                String paramName = p.getName();

                Object provided = null;
                do {
                    if (p.isNamePresent()) {
                        provided = namedProvides.get(paramName);
                        if (provided != null || namedProvides.containsKey(paramName))
                            break;
                    }

                    provided = typedProvides.meet(paramType);
                    if (provided != null || typedProvides.containsKey(paramType))
                        break;

                    provided = _typeDefaults.get(paramType);
                    if (provided != null || _typeDefaults.containsKey(paramType))
                        break;

                    if (! isRequired(p))
                        break;

                    continue C;
                } while (false);

                paramValues[i] = provided;
            }

            @SuppressWarnings("unchecked")
            T instance = (T) ctor.newInstance(paramValues);
            return instance;
        }

        throw new IllegalUsageException("No suitable constructor found.");
    }

}