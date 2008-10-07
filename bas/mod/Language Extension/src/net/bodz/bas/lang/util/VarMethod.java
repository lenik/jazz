package net.bodz.bas.lang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.types.TypesHierMap;
import net.bodz.bas.types.util.Types;

public class VarMethod {

    private final String         name;

    /**
     * Parameter Types Map
     */
    private TypesHierMap<Method> ptmap;

    /**
     * @see Members#allMethods(Class, String)
     * @see Members#allMethods(Class, String,boolean)
     * @see Members#methods(Class, boolean)
     * @see Members#methods(Class, String, boolean)
     */
    public VarMethod(String name, Iterable<Method> methods) {
        this.name = name;
        ptmap = new TypesHierMap<Method>();
        for (Method method : methods) {
            Class<?>[] pt = method.getParameterTypes();
            ptmap.put(pt, method);
        }
    }

    public TypesHierMap<Method> getPTMap() {
        return ptmap;
    }

    public Method findMethod(Class<?>... paramTypes) {
        return ptmap.getParent(paramTypes);
    }

    public Object invoke(Object obj, Class<?>[] paramTypes, Object... params)
            throws NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method method = findMethod(paramTypes);
        if (method == null)
            throw new NoSuchMethodException(name + "("
                    + Types.joinNames(paramTypes) + ")");
        if (obj != null) {
            Class<?> objClass = obj.getClass();
            Class<?> declClass = method.getDeclaringClass();
            if (!declClass.isAssignableFrom(objClass))
                throw new IllegalUsageError(
                        "invoke method of different type, obj=" + objClass
                                + ", decl=" + declClass);
        }
        return Control.invoke(method, obj, params);
    }

    public Object invoke(Object obj, Object... params)
            throws IllegalArgumentException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Class<?>[] paramTypes = Types.getTypes(params);
        return invoke(obj, paramTypes, params);
    }

    public Object _invoke(Object obj, Class<?>[] paramTypes, Object... params) {
        try {
            return invoke(obj, paramTypes, params);
        } catch (IllegalArgumentException e) {
            throw new ReflectException(e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    public Object _invoke(Object obj, Object... params) {
        try {
            return invoke(obj, params);
        } catch (IllegalArgumentException e) {
            throw new ReflectException(e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

}
