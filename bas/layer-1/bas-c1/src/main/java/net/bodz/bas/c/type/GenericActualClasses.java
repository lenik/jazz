package net.bodz.bas.c.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GenericActualClasses {

    static Map<Type, GenericActualClassInfo> map = new HashMap<>();

    public static GenericActualClassInfo getActualInfo(Class<?> clazz) {
        GenericActualClassInfo info = map.get(clazz);
        if (info == null) {
            info = new GenericActualClassInfo(clazz);
            map.put(clazz, info);
        }
        return info;
    }

    public static GenericActualClassInfo getActualInfo(Type type) {
        if (type instanceof Class<?>)
            return getActualInfo((Class<?>) type);
        if (type instanceof ParameterizedType)
            return getActualInfo((ParameterizedType) type);
        throw new UnsupportedOperationException();
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

}
