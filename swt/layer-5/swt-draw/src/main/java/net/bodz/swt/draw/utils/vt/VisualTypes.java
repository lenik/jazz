package net.bodz.swt.draw.utils.vt;

import java.util.HashMap;
import java.util.Map;

public class VisualTypes {

    public static boolean isSupportedType(Class<?> dataType) {
        return registeredTypes.containsKey(dataType);
    }

    public static VisualType getVisualType(Class<?> dataType) {
        Class<?> vtClass = registeredTypes.get(dataType);
        VisualType vt;
        try {
            vt = (VisualType) vtClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return vt;
    }

    public static Class<?> findCompatibleClassSupported(Class<?> derivedType) {
        for (Class<?> baseType : registeredTypes.keySet()) {
            if (baseType.isAssignableFrom(derivedType))
                return baseType;
        }
        return null;
    }

    public static void register(Class<?> visualTypeClass) {
        VisualType queryInst;
        try {
            queryInst = (VisualType) visualTypeClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        register(queryInst);
    }

    public static void register(VisualType visualType) {
        assert visualType != null;
        Class<?> visualTypeClass = visualType.getClass();
        Class<?>[] supportTypes = visualType.supportTypes();
        for (Class<?> supportType : supportTypes) {
            if (registeredTypes.containsKey(supportType)) {
                throw new RuntimeException("One more data type has already been registered.");
            }
            registeredTypes.put(supportType, visualTypeClass);
        }
    }

    public static void unregister(Class<?> visualTypeClass) {
        VisualType queryInst;
        try {
            queryInst = (VisualType) visualTypeClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        unregister(queryInst);
    }

    public static void unregister(VisualType visualType) {
        assert visualType != null;
        Class<?> visualTypeClass = visualType.getClass();
        Class<?>[] supportTypes = visualType.supportTypes();
        for (Class<?> supportType : supportTypes) {
            Class<?> clsOld = registeredTypes.get(supportType);
            if (clsOld == visualTypeClass)
                registeredTypes.remove(supportType);
        }
    }

    public static void unregisterDataType(Class<?> dataType) {
        if (registeredTypes.containsKey(dataType))
            registeredTypes.remove(dataType);
    }

    private static Class<?>[] builtins;
    private static Map<Class<?>, Class<?>> registeredTypes;

    static {
        builtins = new Class<?>[] { VTNumber.class, VTString.class, VTColor.class, };
        registeredTypes = new HashMap<Class<?>, Class<?>>();
        for (Class<?> builtin : builtins) {
            register(builtin);
        }
    }
}
