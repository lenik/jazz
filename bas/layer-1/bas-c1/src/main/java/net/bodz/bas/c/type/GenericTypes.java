package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

public class GenericTypes {

    static Map<Type, GenericActualClassInfo> actualClassCache = new HashMap<>();

    public static GenericActualClassInfo getActualInfo(Class<?> clazz) {
        GenericActualClassInfo info = actualClassCache.get(clazz);
        if (info == null) {
            info = new GenericActualClassInfo(clazz);
            actualClassCache.put(clazz, info);
        }
        return info;
    }

    public static GenericActualClassInfo getActualInfo(Type type) {
        if (type instanceof Class<?>)
            return getActualInfo((Class<?>) type);
        if (type instanceof ParameterizedType)
            return getActualInfo((ParameterizedType) type);
        throw new UnsupportedOperationException("type class: " + type.getClass());
    }

    public static GenericActualClassInfo getActualInfo(ParameterizedType type) {
        Type rawType = type.getRawType();
        GenericActualClassInfo gRaw = getActualInfo(rawType);
        Type[] args = type.getActualTypeArguments();
        return gRaw.otherArgs(args);
    }

    public static GenericActualClassInfo getActualSuperInfo(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null)
            return null;
        GenericActualClassInfo gsuper = getActualInfo(superclass);
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType ptSuper = (ParameterizedType) genericSuperclass;
            Type[] args = ptSuper.getActualTypeArguments();
            return gsuper.otherArgs(args);
        } else {
            return gsuper;
        }
    }

    public static GenericActualClassInfo[] getActualInterfacesInfo(Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        GenericActualClassInfo[] wrappers = new GenericActualClassInfo[genericInterfaces.length];
        for (int i = 0; i < genericInterfaces.length; i++) {
            GenericActualClassInfo ginterface = getActualInfo(interfaces[i]);
            Type genericInterface = genericInterfaces[i];
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericInterface;
                Type[] args = pt.getActualTypeArguments();
                wrappers[i] = ginterface.otherArgs(args);
            } else {
                wrappers[i] = ginterface;
            }
        }
        return wrappers;
    }

    public static boolean isComplete(Type type) {
        if (type instanceof TypeVariable<?>)
            return false;
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            TypeVariable<?>[] vars = clazz.getTypeParameters();
            return vars.length == 0;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type[] args = pt.getActualTypeArguments();
            for (Type arg : args)
                if (!isComplete(arg))
                    return false;
            return true;
        }
        if (type instanceof WildcardType) {
            WildcardType wtype = (WildcardType) type;
            Type[] upperBounds = wtype.getUpperBounds();
            for (Type bound : upperBounds)
                if (!isComplete(bound))
                    return false;

            Type[] lowerBounds = wtype.getLowerBounds();
            for (Type bound : lowerBounds)
                if (!isComplete(bound))
                    return false;
            return true;
        }
        // unknown type
        return false;
    }

    public static Class<?> getDefaultUpperBound(Type arg) {
        return getDefaultUpperBound(arg, null);
    }

    public static Class<?> getDefaultUpperBound(Type arg, Class<?> subclassHint) {
        if (arg instanceof Class<?>)
            return (Class<?>) arg;
        if (arg instanceof TypeVariable<?>) {
            TypeVariable<?> argVar = (TypeVariable<?>) arg;
            Type[] argUpperBounds = argVar.getBounds();
            if (argUpperBounds.length > 0) {
                if (subclassHint != null)
                    for (int i = 0; i < argUpperBounds.length; i++) {
                        Class<?> upperBound = getDefaultUpperBound(argUpperBounds[i]);
                        if (upperBound.isAssignableFrom(subclassHint))
                            return upperBound;
                    }
                else
                    return getDefaultUpperBound(argUpperBounds[0]);
            }
        }
        if (arg instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) arg;
            Type rawType = pt.getRawType();
            return getDefaultUpperBound(rawType, subclassHint);
        }
        return Object.class;
    }

}
